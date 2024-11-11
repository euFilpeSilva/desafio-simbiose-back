export interface Page<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
}

export interface PessoaResponse {
    id: number;
    nome: string;
    email: string;
    nascimento: string;
}