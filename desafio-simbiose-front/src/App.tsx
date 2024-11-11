import Form from "./components/Form.tsx";
import {PessoaResponse} from "./types/Pessoa-response.tsx";
import {useState} from "react";
import Modal from "./components/Modal.tsx";
import {deletarPessoa, salvarPessoa} from "./services/userService.tsx";

function App() {
    const [selectedPessoa] = useState<PessoaResponse | null>(null);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [feedbackMessage, setFeedbackMessage] = useState<string | null>(null);

    const confirmDelete = async () => {
        if (selectedPessoa) {
            await deletarPessoa(selectedPessoa.id);
            setIsDeleteModalOpen(false);
        }
    };

    const handleFormSubmit = async (data: PessoaResponse) => {
        try {
            await salvarPessoa(data);
            setFeedbackMessage("Dados salvos com sucesso!");
        } catch (error) {
            setFeedbackMessage("Erro ao salvar os dados.");
        }
        // Clear the feedback message after a few seconds
        setTimeout(() => setFeedbackMessage(null), 3000);
    };

    return (
            <div className="bg-slate-900 min-h-screen w-full flex flex-col items-center justify-center p-4 relative">
            {feedbackMessage && (
                <div className="absolute top-4 right-4 p-2 text-white bg-green-500 rounded">
                    {feedbackMessage}
                </div>
            )}
                <Form onSubmit={handleFormSubmit}/>
                <Modal isOpen={isEditModalOpen} onClose={() => setIsEditModalOpen(false)} title="Editar Pessoa">
                    {selectedPessoa && (
                        <Form initialData={selectedPessoa} onSubmit={salvarPessoa} isEditMode={true}/>
                    )}
                </Modal>
                <Modal isOpen={isDeleteModalOpen} onClose={() => setIsDeleteModalOpen(false)} title="Confirmar Deleção">
                    {selectedPessoa && (
                        <div>
                            <p>Tem certeza que deseja deletar {selectedPessoa.nome}?</p>
                            <div className="flex justify-end gap-2 mt-4">
                                <button onClick={() => setIsDeleteModalOpen(false)}
                                        className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">
                                    Cancelar
                                </button>
                                <button onClick={confirmDelete}
                                        className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                                    Deletar
                                </button>
                            </div>
                        </div>
                    )}
                </Modal>
            </div>
    )
}

export default App;