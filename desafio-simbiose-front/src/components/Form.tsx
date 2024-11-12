import {useEffect, useState} from "react";
import {PessoaResponse} from "../types/Pessoa-response.tsx";

interface FormProps {
    initialData?: PessoaResponse;
    onSubmit: (data: PessoaResponse) => void;
    isEditMode?: boolean;
}

const Form = ({initialData, onSubmit, isEditMode}: FormProps) => {
    const [formData, setFormData] = useState<PessoaResponse>(initialData || {
        id: 0,
        nome: "",
        email: "",
        nascimento: ""
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (initialData) {
            setFormData(initialData);
        }
    }, [initialData]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        try {
            await onSubmit(formData);
            setFormData({
                id: 0,
                nome: "",
                email: "",
                nascimento: ""
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col bg-white p-4 rounded w-full mx-auto mt-12">
            <h1 className="text-xl font-bold mb-4">Cadastro</h1>
            <div className="flex flex-col md:flex-row mb-4 gap-3.5 items-center">
                <div className="w-full md:w-1/3 mb-4 md:mb-0">
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
                        disabled={loading}
                    />
                </div>
                <div className="w-full md:w-1/3 mb-4 md:mb-0">
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
                        disabled={loading}
                    />
                </div>
                <div className="w-full md:w-1/6 mb-4 md:mb-0 mr-auto">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="nascimento">
                        Nascimento
                    </label>
                    <input
                        type="date"
                        name="nascimento"
                        value={formData.nascimento}
                        onChange={handleChange}
                        className="w-full px-3 py-2 border rounded"
                        required
                        disabled={loading}
                    />
                </div>
                <div className="flex justify-end items-center w-full md:w-auto mt-5">
                    <button type="submit"
                            className="bg-neutral-800 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded"
                            disabled={loading}>
                        {isEditMode ? "Atualizar" : "Salvar"}
                    </button>
                </div>
            </div>
        </form>
    );
};

export default Form;