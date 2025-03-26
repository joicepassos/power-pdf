"use client";

import React, { useState } from "react";
import FileSelect from "./files/FileSelect";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";

const UploadForm: React.FC = () => {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (selectedFile) {
            console.log("File selected:", selectedFile.name);
        }
    };

    return (
        <div className="flex w-full items-center justify-center p-4">
            <form onSubmit={handleSubmit} className="flex flex-col w-full max-w-lg p-6 rounded-lg gap-4 ">
                
                {/* Área de Upload */}
                <FileSelect />

                {/* Nome do PDF */}
                <InputText
                    type="text"
                    className="p-inputtext-sm w-full"
                    placeholder="Nome do PDF"
                    id="pdfName"
                />

                {/* Botão de Merge */}
                <Button
                    label="Realizar o Merge"
                    severity="primary"
                    className="w-full"
                    size="small"
                    onClick={handleSubmit}
                />
            </form>
        </div>
    );
};

export default UploadForm;
