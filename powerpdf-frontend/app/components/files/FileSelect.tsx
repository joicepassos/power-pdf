"use client";

import React, { useState } from "react";
import { FileUpload } from "primereact/fileupload";

const FileSelect: React.FC = () => {
    const [fileName, setFileName] = useState<string | null>(null);
    const [filePath, setFilePath] = useState<string | null>(null);
    const [isUploading, setIsUploading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const onUpload = async (event: any) => {
        const uploadedFile = event.files[0];

        if (!uploadedFile) return;

        setIsUploading(true);
        setError(null);

        const formData = new FormData();
        formData.append("file", uploadedFile);

        try {
            const response = await fetch("/api/upload", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Erro ao enviar o arquivo.");
            }

            const data = await response.json();
            setFileName(data.newName);
            setFilePath(data.path);

            console.log("Upload concluído:", data);
        } catch (error) {
            setError("Erro ao fazer upload do arquivo.");
            console.error(error);
        } finally {
            setIsUploading(false);
        }
    };

    const emptyTemplate = (
        <div className="flex flex-col items-center justify-center min-h-[100px] border-2 border-dashed border-gray-500 rounded-lg p-4">
            <span className="text-sm text-gray-600">Selecione ou arraste um arquivo PDF.</span>
        </div>
    );

    return (
        <div className="flex flex-col w-full items-center justify-center">
            <FileUpload
                name="file"
                accept="application/pdf"
                maxFileSize={50000000}
                customUpload
                auto
                multiple
                chooseOptions={{
                    icon: "pi pi-fw pi-file-pdf",
                    label: "Selecionar PDF",
                    className: "p-button-sm",
                }}
                onSelect={onUpload}
                emptyTemplate={emptyTemplate}
                className="w-full"
            />
        </div>
    );
};

export default FileSelect;
