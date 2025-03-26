/* eslint-disable @typescript-eslint/no-unused-vars */
import type { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    const { method } = req;

    switch (method) {
        case 'POST':
            const apiUrl = `${process.env.API_STORAGE_URL}/upload?temporary=true&filePath="merges/pdf"`;

            console.log('Enviando arquivo para:', apiUrl);
            try {

                const response = await fetch(apiUrl, {
                  method: 'POST',
                  headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${process.env.API_STORAGE_SERVICE_AUTHENTICATION}`, 
                  },
                  body: JSON.stringify(req.body),
                });
                console.log('Arquivo enviado:', response);


                const data = await response.json();
                console.log('Arquivo enviado:', data);

                res.status(response.status).json(data);
            } catch (error) {
                res.status(500).json({ error: 'Erro ao processar a requisição' });
            }
            break;
        default:
            res.setHeader('Allow', ['POST']);
            res.status(405).end(`Método ${method} não permitido`);
            break;
    }
}

