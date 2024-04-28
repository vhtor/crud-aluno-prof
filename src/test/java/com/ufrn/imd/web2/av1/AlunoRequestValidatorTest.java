package com.ufrn.imd.web2.av1;

import com.ufrn.imd.web2.av1.enums.Genero;
import com.ufrn.imd.web2.av1.exception.DataValidationException;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.AlunoRequest;
import com.ufrn.imd.web2.av1.service.AlunoService;
import com.ufrn.imd.web2.av1.utils.DateUtils;
import com.ufrn.imd.web2.av1.validator.AlunoRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AlunoRequestValidatorTest {
    private AlunoRequestValidator.AlunoRequestValidatorBuilder validatorBuilder;
    private AlunoService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(AlunoService.class);
        validatorBuilder = AlunoRequestValidator.builder().service(service);
    }

    @Test
    public void validate_DeveSerBemSucedidoComRequestCreateValida() {
        // ARRANGE
        final var request = buildRequestValida();

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        assertDoesNotThrow(() -> validator.validateCreate(request));
    }

    @Test
    public void validate_DeveJogarExceptionComNomeNulo() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setNome(null);

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Nome é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComNomeVazio() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setNome("");

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Nome é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComNomeComEspaco() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setNome("             ");

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Nome é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComCpfNulo() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setCpf(null);

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("CPF é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComCursoNulo() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setCurso("");

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Curso é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComGeneroNulo() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setGenero(null);

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Gênero é obrigatório", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComDataNascimentoNula() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setDataNascimento(null);

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Data de nascimento é obrigatória", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComDataNascimentoAntesDe1900() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setDataNascimento(DateUtils.on(1, 1, 1899));

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Data de nascimento não pode ser menor que 01/01/1900", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComDataNascimentoAposDataAtual() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setDataNascimento(DateUtils.plusDays(new Date(), 1));

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Data de nascimento não pode ser maior que a data atual", ex.getMessage());
    }

    @Test
    public void validate_DeveJogarExceptionComMatriculaNula() {
        // ARRANGE
        final var request = buildRequestValida();
        request.setMatricula(null);

        final var validator = validatorBuilder
                .context(Context.CREATE)
                .request(request)
                .build();

        // ACT & ASSERT
        final var ex = Assertions.assertThrows(
                DataValidationException.class, () -> validator.validateCreate(request));

        Assertions.assertEquals("Matrícula é obrigatória", ex.getMessage());
    }

    public AlunoRequest buildRequestValida() {
        return AlunoRequest.builder()
                .nome("Nome válido")
                .cpf("CPF válido")
                .curso("Bacharelado em Tecnologia da Informação")
                .genero(Genero.OUTRO)
                .dataNascimento(new Date())
                .matricula(123456L)
                .build();
    }
}
