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
    downloadUrl: string | null;
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
        if (rowData.downloadUrl) {
            return (
                <a href={rowData.downloadUrl} download>
                    <button className="p-button p-component p-button-text">Download</button>
                </a>
            );
        } else {
            return <span>{rowData.status === 'completed' ? 'Loading' : 'Pendente'}</span>;
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