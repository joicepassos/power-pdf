"use client";

import React, { useState } from 'react';
import FileSelect from './files/FileSelect';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';

const UploadForm: React.FC = () => {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files.length > 0) {
            setSelectedFile(event.target.files[0]);
        }
    };

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (selectedFile) {
            // Handle file upload logic here
            console.log('File selected:', selectedFile.name);
        }
    };

    return (
        <div className="flex w-full overflow-hidden items-center justify-center">

        <form onSubmit={handleSubmit} className="flex flex-col w-full max-w-sm p-6 rounded-lg gap-4">

            <FileSelect />

            <div className="flex flex-col items-center gap-3">
                <InputText
                    type="text"
                    className="p-inputtext-lg w-full"
                    placeholder="Nome do Pdf"
                    id="pdfName"
                />
            </div>

            <div className="flex flex-col items-center gap-3">
                <Button
                    label="Realizar o Merge"
                    iconPos="right"
                    severity='primary'
                    className="w-full"
                    onClick={handleSubmit}
                />
            </div>
        </form>
        </div>
    );
};

export default UploadForm;
