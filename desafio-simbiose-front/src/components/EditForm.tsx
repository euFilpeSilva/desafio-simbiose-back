import { useEffect, useState } from "react";
import { PessoaResponse } from "../types/Pessoa-response.tsx";

interface EditFormProps {
    initialData: PessoaResponse;
    onSubmit: (data: PessoaResponse) => void;
}

const EditForm = ({ initialData, onSubmit }: EditFormProps) => {
    const [formData, setFormData] = useState<PessoaResponse>(initialData);

    useEffect(() => {
        setFormData(initialData);
    }, [initialData]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col bg-white p-4 rounded w-full mx-auto space-y-4">
            <div className="flex flex-col w-full">
                <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="nome">
                    Nome
                </label>
                <input
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border rounded"
                    required
                />
            </div>
            <div className="flex flex-col w-full">
                <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                    E-mail
                </label>
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border rounded"
                    required
                />
            </div>
            <div className="flex flex-col w-full">
                <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="nascimento">
                    Nascimento
                </label>
                <input
                    type="date"
                    name="nascimento"
                    value={formData.nascimento ? formData.nascimento.split('T')[0] : ''}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border rounded"
                    required
                />
            </div>
            <div className="flex justify-end items-center w-full mt-5">
                <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Atualizar
                </button>
            </div>
        </form>
    );
};

export default EditForm;