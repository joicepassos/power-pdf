"use client";

import React, { useState, useEffect } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import axios from 'axios';

interface Merge {
    id: number;
    name: string;
    date: string;
    status: string;
    link: string | null;
}

const MergedFiles: React.FC = () => {
    const [merges, setMerges] = useState<Merge[]>([]);

    useEffect(() => {
        axios.get('/api/report')
            .then(response => {
                setMerges(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the merges!', error);
            });
    }, []);

    const downloadBodyTemplate = (rowData: Merge) => {
        if (rowData.link) {
            return (
                <a href={rowData.link} download>
                    <button className="p-button p-component p-button-text">Download</button>
                </a>
            );
        } else {
            return <span>Processando</span>;
        }
    };

    return (
        <div className="flex w-full items-center justify-center p-4">
            <DataTable value={merges}>
                <Column field="createdAt" header="Date" />
                <Column field="name" header="Nome" />
                <Column body={downloadBodyTemplate} header="Download" />
            </DataTable>
        </div>
    );
}

export default MergedFiles;