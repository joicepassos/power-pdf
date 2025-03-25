"use client";
import { createContext, useContext, useState, ReactNode } from 'react';

interface UploadedFile {
  name: string;
  size: number;
  type: string;
  id?: string;
  url?: string;
}

interface Merge {
  id: string;
  name: string;
  createdAt: Date;
  files: UploadedFile[];
  downloadUrl: string;
}

interface FileMergeContextData {
  merges: Merge[];
  addMerge: (name: string, files: UploadedFile[]) => Promise<void>;
  filterMergesByDate: (startDate: Date, endDate: Date) => void;
}

const FileMergeContext = createContext<FileMergeContextData>({} as FileMergeContextData);

interface FileMergeProviderProps {
  children: ReactNode;
}

export function FileMergeProvider({ children }: FileMergeProviderProps) {
  const [merges, setMerges] = useState<Merge[]>([]);
  const [filteredMerges, setFilteredMerges] = useState<Merge[]>([]);

  const addMerge = async (name: string, files: UploadedFile[]) => {

    const newMerge: Merge = {
      id: Math.random().toString(36).substring(7),
      name,
      createdAt: new Date(),
      files,
      downloadUrl: '#' 
    };

    setMerges(prevMerges => [...prevMerges, newMerge]);
    setFilteredMerges(prevMerges => [...prevMerges, newMerge]);
  };

  const filterMergesByDate = (startDate: Date, endDate: Date) => {
    const filtered = merges.filter(merge => {
      const mergeDate = new Date(merge.createdAt);
      return mergeDate >= startDate && mergeDate <= endDate;
    });

    setFilteredMerges(filtered);
  };

  return (
    <FileMergeContext.Provider
      value={{
        merges: filteredMerges,
        addMerge,
        filterMergesByDate
      }}
    >
      {children}
    </FileMergeContext.Provider>
  );
}

export function useFileMerge() {
  const context = useContext(FileMergeContext);

  if (!context) {
    throw new Error('useFileMerge must be used within a FileMergeProvider');
  }

  return context;
}