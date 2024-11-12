import { useState, useEffect } from "react";
import { PessoaResponse } from "../types/Pessoa-response.tsx";
import { FaEdit, FaTrash } from "react-icons/fa";
import { listarPessoas } from "../services/userService.tsx";
import {format} from "date-fns";

interface ListaPessoasProps {
    onEdit?: (pessoa: PessoaResponse) => void;
    loading: boolean;
    error: string | null;
    onDelete: (pessoa: PessoaResponse) => void;
    pessoas: PessoaResponse[];
}
const ListaPessoas = ({ onEdit, onDelete }: ListaPessoasProps) => {
    const [pessoas, setPessoas] = useState<PessoaResponse[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchPessoas = async () => {
            setLoading(true);
            try {
                const response = await listarPessoas(0, 10);
                setPessoas(response.content);
            } catch (error) {
                setError("Erro ao listar pessoas.");
            } finally {
                setLoading(false);
            }
        };

        fetchPessoas();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div className="w-full mx-auto">
            <h1 className="text-xl font-bold mb-4">Lista de Cadastrados</h1>
            <div className="overflow-x-auto scrollbar-hide rounded-lg">
                <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="py-2 px-4 border-b">Nome</th>
                        <th className="py-2 px-4 border-b">E-mail</th>
                        <th className="py-2 px-4 border-b">Nascimento</th>
                        <th className="py-2 px-4 border-b">Ações</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {pessoas.map((pessoa, index) => (
                        <tr key={pessoa.id} className={index === pessoas.length  - 1 ? 'border-b last:border-b' : 'border-b'}>
                            <td className="py-2 px-4 border-b">{pessoa.nome}</td>
                            <td className="py-2 px-4 border-b">{pessoa.email}</td>
                            <td className="py-2 px-4 border-b">{format(new Date(pessoa.nascimento), 'dd/MM/yyyy')}</td>
                            <td className="py-4 px-4 flex gap-2">
                            <button onClick={() => onEdit && onEdit(pessoa)} className="text-blue-500 hover:text-blue-700">
                                    <FaEdit />
                                </button>
                                <button onClick={() => onDelete(pessoa)} className="text-red-500 hover:text-red-700">
                                    <FaTrash />
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ListaPessoas;