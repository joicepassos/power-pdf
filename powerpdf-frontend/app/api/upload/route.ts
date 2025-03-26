import type { NextApiRequest, NextApiResponse } from 'next';
import { IncomingForm } from 'formidable';
import fs from 'fs';

export const config = {
    api: {
        bodyParser: false, // Desabilitar o bodyParser para lidar com FormData
    },
};

export async function POST(req: NextApiRequest, res: NextApiResponse) {
    const form = new IncomingForm();

    form.parse(req, async (err, fields, files) => {
        if (err) {
            res.status(500).json({ error: 'Erro ao processar o formulário' });
            return;
        }

        if (!files.file) {
            res.status(400).json({ error: 'Nenhum arquivo foi enviado' });
            return;
        }
        const file = files.file[0];
        const apiUrl = `${process.env.API_STORAGE_URL}/v1/files/upload?temporary=true&filePath="merges/pdf"`;

        const formData = new FormData();
        const fileStream = fs.createReadStream(file.filepath);
        const chunks: Uint8Array[] = [];
        for await (const chunk of fileStream) {
            chunks.push(chunk);
        }
        const fileBlob = new Blob(chunks);
        formData.append('file', fileBlob, file.originalFilename || undefined);

        try {
            const response = await fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${process.env.API_STORAGE_SERVICE_AUTHENTICATION}`,
                },
                body: formData,
            });

            const data = await response.json();
            console.log('Arquivo enviado:', data);

            res.status(response.status).json(data);
        } catch (error) {
            console.error(error);
            res.status(500).json({ error: 'Erro ao processar a requisição' });
        }
    });
}