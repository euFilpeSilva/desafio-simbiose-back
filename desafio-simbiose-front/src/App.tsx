import { useState, useEffect } from "react";
import { FaList } from "react-icons/fa";
import Form from "./components/Form.tsx";
import Modal from "./components/Modal.tsx";
import ListaPessoas from "./components/Listagem.tsx";
import { PessoaResponse } from "./types/Pessoa-response.tsx";
import { deletarPessoa, salvarPessoa, listarPessoas } from "./services/userService.tsx";

function App() {
    const [selectedPessoa] = useState<PessoaResponse | null>(null);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [feedbackMessage, setFeedbackMessage] = useState<string | null>(null);
    const [isListVisible, setIsListVisible] = useState(false);
    const [pessoas, setPessoas] = useState<PessoaResponse[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [isEditMode, setIsEditMode] = useState(false);
    const [listKey, setListKey] = useState(0);

    const fetchPessoas = async () => {
        setLoading(true);
        try {
            const response = await listarPessoas(0, 10);
            setPessoas(response.content);
            setListKey(prevKey => prevKey + 1);
        } catch (error) {
            setError("Erro ao listar pessoas.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPessoas();
    }, []);

    const confirmDelete = async () => {
        if (selectedPessoa) {
            await deletarPessoa(selectedPessoa.id);
            setIsDeleteModalOpen(false);
            await fetchPessoas();
        }
    };

    const handleFormSubmit = async (data: PessoaResponse) => {
        try {
            await salvarPessoa(data);
            setFeedbackMessage("Dados salvos com sucesso!");
            await fetchPessoas();
        } catch (error) {
            setFeedbackMessage("Erro ao salvar os dados.");
        }
        setTimeout(() => setFeedbackMessage(null), 3000);
    };

    const toggleListVisibility = () => {
        setIsListVisible(!isListVisible);
    };

    return (
        <div className="bg-slate-900 min-h-screen w-full flex flex-col items-center justify-center p-4 relative">
            <button onClick={toggleListVisibility} className="absolute top-4 left-4 text-white text-2xl md:hidden">
                <FaList />
            </button>
            {feedbackMessage && (
                <div className="absolute top-4 right-4 p-2 text-white bg-green-500 rounded">
                    {feedbackMessage}
                </div>
            )}
            <div className="w-11/12 flex flex-col items-center gap-4">
                    <Form onSubmit={handleFormSubmit} isEditMode={isEditMode}/>
                <div className={`w-full bg-white p-4 rounded shadow-2xl transition-all duration-500 ${isListVisible ? 'block' : 'hidden'} md:block`}>
                    <ListaPessoas key={listKey} pessoas={pessoas} loading={loading} error={error} onDelete={() => {}} />
                </div>
            </div>
            <Modal isOpen={isEditModalOpen} onClose={() => setIsEditModalOpen(false)} title="Editar Pessoa">
                {selectedPessoa && (
                    <Form initialData={selectedPessoa} onSubmit={salvarPessoa} isEditMode={true} />
                )}
            </Modal>
            <Modal isOpen={isDeleteModalOpen} onClose={() => setIsDeleteModalOpen(false)} title="Confirmar Deleção">
                {selectedPessoa && (
                    <div>
                        <p>Tem certeza que deseja deletar {selectedPessoa.nome}?</p>
                        <div className="flex justify-end gap-2 mt-4">
                            <button onClick={() => setIsDeleteModalOpen(false)} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">
                                Cancelar
                            </button>
                            <button onClick={confirmDelete} className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                                Deletar
                            </button>
                        </div>
                    </div>
                )}
            </Modal>
        </div>
    );
}

export default App;