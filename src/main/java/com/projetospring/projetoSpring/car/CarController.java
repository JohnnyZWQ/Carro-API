package com.projetospring.projetoSpring.car;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Define que essa classe é um controller REST que escutará requisições a partir do endpoint /carro.
 */
@RestController
@RequestMapping("/carro")
public class CarController {

    @Autowired
    private ICarRepository carRepository;

    /**
     * Endpoint POST para cadastrar um novo carro.
     * Se o modelo do carro já estiver cadastrado, retorna erro.
     * Caso contrário, criptografa a senha, salva o carro no banco e retorna o carro criado.
     */
    @PostMapping("/novo")
    private ResponseEntity criarCarro(@RequestBody CarModel carModel, HttpServletRequest request) {
        var carroExiste = this.carRepository.findByNome(carModel.getNome());

        if (carroExiste != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Carro já cadastrado");
        } else {
            var senhaHash = BCrypt.withDefaults()
                    .hashToString(12, carModel.getSenha().toCharArray());
            carModel.setSenha(senhaHash);

            var criado = this.carRepository.save(carModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        }
    }

    /**
     * Endpoint GET para listar todos os carros cadastrados.
     * @return Lista com todos os carros do banco.
     */
    @GetMapping("/listar")
    public List<CarModel> listarCarros() {
        List<CarModel> carrosCadastrados = carRepository.findAll();
        return carrosCadastrados;
    }

    /**
     * Endpoint PUT para atualizar os dados de um carro.
     */
    @PutMapping("/atualizar")
    public ResponseEntity atualizarCarro(@RequestBody CarModel carModel) {
        var atualizado = this.carRepository.save(carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(atualizado);
    }

    /**
     * Endpoint DELETE para excluir um carro pelo ID.
     * @param id UUID do carro a ser deletado.
     */
    @DeleteMapping("/delete/{id}")
    public void deletarCarro(@PathVariable UUID id) {
        carRepository.deleteById(id);
    }
}
