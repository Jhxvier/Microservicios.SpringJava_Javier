package com.taller.ms_pedidos.repository;

import com.taller.ms_pedidos.enums.EstadoPedido;
import com.taller.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //cuenta los pedidos activos de un usuario (regla de limite de pedidos)
    long countByUsuarioIdAndEstado(Long usuarioId, EstadoPedido estado);

}