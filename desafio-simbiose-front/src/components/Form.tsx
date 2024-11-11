import React, { useState, useEffect } from 'react';
import { PessoaResponse } from "../types/Pessoa-response.tsx";

interface FormProps {
    initialData?: PessoaResponse;
    onSubmit: (data: PessoaResponse) => void;
    onFormReset?: () => void;
    isEditMode?: boolean;
}

const Form = ({ initialData, onSubmit }: FormProps) => {
    const formatDate = (date: string) => {
        if (date.includes('-')) {
            return date;
        }
        const [day, month, year] = date.split('/');
        return `${year}-${month}-${day}`;
    };

    const [formData, setFormData] = useState<PessoaResponse>({
        id: initialData?.id || 0,
        nome: initialData?.nome || '',
        email: initialData?.email || '',
        nascimento: initialData?.nascimento || ''
    });

    useEffect(() => {
        if (initialData) {
            setFormData({
                ...initialData,
                nascimento: initialData.nascimento.split('-').reverse().join('-')
            });
        }
    }, [initialData]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const formattedData = {
            ...formData,
            nascimento: formatDate(formData.nascimento)
        };
        onSubmit(formattedData);
        // Reset form data
        setFormData({
            id: 0,
            nome: '',
            email: '',
            nascimento: ''
        });
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4 bg-gray-100 p-5 rounded-lg w-full max-w-lg">
            <div>
                <label htmlFor="nome" className="block text-sm font-medium text-gray-700">Nome</label>
                <input
                    type="text"
                    id="nome"
                    name="nome"
                    value={formData.nome}
                    onChange={handleChange}
                    required
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
            </div>
            <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-700">E-mail</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
            </div>
            <div>
                <label htmlFor="nascimento" className="block text-sm font-medium text-gray-700">Nascimento</label>
                <input
                    type="text"
                    id="nascimento"
                    name="nascimento"
                    value={formData.nascimento}
                    onChange={handleChange}
                    required
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
            </div>
            <div className="flex justify-end space-x-2">
                <button type="submit" className="bg-cyan-800 hover:bg-gray-600 text-white font-bold py-2 px-4 rounded">
                    Salvar
                </button>
            </div>
        </form>
    );
};

export default Form;