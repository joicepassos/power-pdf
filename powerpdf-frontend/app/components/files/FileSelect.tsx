"use client";

import React, { useState } from 'react';
import { FileUpload } from 'primereact/fileupload';

const FileSelect: React.FC = () => {
    const [file, setFile] = useState<File | null>(null);

    const onUpload = (event: any) => {
        setFile(event.files[0]);
        // Handle the file upload logic here
        console.log('File selected:', event.files[0]);
    };

    const emptyTemplate = (
        <div className="flex flex-col items-center justify-center min-h-[200px] -w[200px] border-2 border-dashed border-gray-300 rounded-lg">

            <span className="text-sm text-gray-600">Selecione ou arraste os PDF's.</span>
        </div>
    );

    return (
        <div className="flex w-full overflow-hidden items-center justify-center">
            <FileUpload
                emptyTemplate={emptyTemplate}
                name="demo[]"
                customUpload
                headerTemplate={
                    <div>

                    </div>
                }
                uploadHandler={onUpload}
                auto
                className="relative w-full cursor-pointer"
                chooseLabel=""
            />



        </div>


    );
};

export default FileSelect;