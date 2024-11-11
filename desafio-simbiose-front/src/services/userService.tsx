import {User} from "../types/User.ts";
import {Page, PessoaResponse} from "../types/Pessoa-response.tsx";

const API_URL = 'http://localhost:8080/pessoa';

export const salvarPessoa = async (data: User): Promise<void> => {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error('Erro ao cadastrar pessoa.');
    }
};

export const listarPessoas = async (page: number, size: number): Promise<Page<PessoaResponse>> => {
    const response = await fetch(`${API_URL}?page=${page}&size=${size}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        throw new Error('Erro ao listar pessoas.');
    }

    return response.json();
};

export const editarPessoa = async (id: number, data: User): Promise<void> => {
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error('Erro ao editar pessoa.');
    }
};


export const deletarPessoa = async (id: number): Promise<void> => {
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        throw new Error('Erro ao deletar pessoa.');
    }
};
