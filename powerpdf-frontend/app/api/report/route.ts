import { NextRequest, NextResponse } from 'next/server';
import axios from 'axios';

export async function GET(req: NextRequest) {
    const url = `${process.env.API_FILES_SERVICE_URL}/v1/merges/report?page=1&size=10`;

    try {
        const response = await axios.get(url, {
            headers: {
                'Authorization': `Bearer ${process.env.API_STORAGE_SERVICE_AUTHENTICATION}`,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        });
        return NextResponse.json(response.data, { status: 200 });
    } catch (error) {
        console.error('Failed to fetch data from the API:', error);
        return NextResponse.json({ error: 'Failed to fetch data from the API' }, { status: 500 });
    }
}

export async function POST(req: NextRequest) {
    return NextResponse.json({ error: 'Method not allowed' }, { status: 405 });
}