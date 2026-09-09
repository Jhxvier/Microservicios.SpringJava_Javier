package com.taller.ms_pedidos.validator;

import com.taller.ms_pedidos.exception.CantidadExcedeLimiteException;
import com.taller.ms_pedidos.exception.LimitePedidosActivosException;
import com.taller.ms_pedidos.exception.PedidoYaCanceladoException;
import com.taller.ms_pedidos.enums.EstadoPedido;
import com.taller.ms_pedidos.model.Pedido;
import com.taller.ms_pedidos.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

//REGLAS DE NEGOCIO
@Component
@Data
@AllArgsConstructor
public class PedidoValidator {

    private static final int MAX_CANTIDAD = 20;
    private static final int MAX_PEDIDOS_ACTIVOS = 5;

    //inyección de dependencias
    private final PedidoRepository pedidoRepository;

    //la cantidad no puede superar el limite por pedido
    public void checkCantidadMaxima(Integer cantidad) {
        if (cantidad > MAX_CANTIDAD) {
            throw new CantidadExcedeLimiteException(MAX_CANTIDAD);
        }
    }

    //el usuario no puede tener mas de X pedidos activos (CONFIRMADO)
    public void checkLimitePedidosActivos(Long usuarioId) {
        long activos = pedidoRepository.countByUsuarioIdAndEstado(usuarioId, EstadoPedido.CONFIRMADO);
        if (activos >= MAX_PEDIDOS_ACTIVOS) {
            throw new LimitePedidosActivosException(MAX_PEDIDOS_ACTIVOS);
        }
    }

    //un pedido ya cancelado no se puede cancelar de nuevo
    public void checkPedidoNoCancelado(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.CANCELADO) {
            throw new PedidoYaCanceladoException(pedido.getId());
        }
    }

}