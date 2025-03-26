/* eslint-disable*/
import { NextRequest, NextResponse } from 'next/server';
import { IncomingForm } from 'formidable';
import fs from 'fs';

export const config = {
    api: {
        bodyParser: false, // Desabilitar o bodyParser para lidar com FormData
    },
};

export async function POST(req: NextRequest) {
   
}