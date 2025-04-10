package com.projetospring.projetoSpring.user; // Define o pacote onde esta interface está localizada.
import java.util.UUID; // Importa a classe UUID para uso como identificador único.
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para operações de banco de dados.

/**
 * Interface que define um repositório JPA para a entidade UserModel.
 * JpaRepository já fornece métodos básicos para operações CRUD (Create, Read, Update, Delete).
 */
public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    /**
     * Método para buscar um usuário pelo nome.
     * O Spring Data JPA interpreta automaticamente este método e gera a consulta correspondente.
     * @return Um objeto UserModel correspondente ao nome informado.
     */
    UserModel findByname(String name);
}
