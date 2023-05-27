package TheBigDev;

import TheBigDev.modelo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class TheBigDevController {
    public static Datos datos;

    public TheBigDevController() {
        datos = new Datos();
    }

    public void addArticulo(String codigo, String descripcion, float precioVenta, float gastoEnvio, int tiempo) {
        // creamos un Articulo
        Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastoEnvio, tiempo);

        // si el mismo código de articulo existe en ListaArticulos devolvermos un error
        if( datos.getListaArticulos().existeArticulo(articulo) != null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ya existe un articulo añadido con codigo: " + articulo.getCodigo());
            alert.showAndWait();
            return;
        }
        try {
            // añadimos el articulo a la lista
            datos.getListaArticulos().add(articulo);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al añadir el articulo a la lista de articulos: " + e);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText("Articulo añadido con codigo: " + articulo.getCodigo());
        alert.showAndWait();
    }

    public void addCliente(String email,String nif,String nombre,String domicilio, Boolean premium) {

        // creamos un Articulo
        Cliente cliente;
        // creamos un Cliente según el tipo
        if( premium ) {
            cliente = new ClientePremium(email, nif, nombre, domicilio);
        } else {
            cliente = new ClienteEstandar(email, nif, nombre, domicilio);
        }

        // si existe un cliente con el mismo email devolvemos un error
        if( datos.getListaClientes().existeCliente(cliente) != null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ya existe un cliente con email: "+cliente.getEmail());
            alert.showAndWait();
            return;
        }
        try {
            // añadimos el cliente a la lista
            datos.getListaClientes().add(cliente);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al añadir el cliente a la lista de clientes: " + e);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText("Cliente añadido con email: " + cliente.getEmail());
        alert.showAndWait();
    }

    public void addPedido(Integer numero,String email,String codigo,Integer cantidad,Boolean enviado) {

        // otenemos el cliente
        Cliente cliente = datos.getListaClientes().existeEmailCliente(email);
        if( cliente == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No existe cliente con email: "+email);
            alert.showAndWait();
            return;
        }
        // obtenemos el articulo
        Articulo articulo = datos.getListaArticulos().existeCodigoArticulo(codigo);
        if( articulo == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No existe articulo con codigo: "+codigo);
            alert.showAndWait();
            return;
        }
        // creamos el pedido
        LocalDateTime fechaHora = LocalDateTime.now();
        Pedido pedido = new Pedido(numero, cliente, articulo, cantidad, fechaHora, enviado);

        // si el mismo código de pedido existe devolvermos un error
        if( datos.getListaPedidos().existeNumeroPedido(pedido.getNumero()) != null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ya existe un pedido con codigo: "+cliente.getEmail());
            alert.showAndWait();
            return;
        }
        try {
            // añadimos el pedido a la lista
            datos.getListaPedidos().add(pedido);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al añadir el pedido a la lista de pedidos: " + e);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText("Pedido añadido con numero: " + pedido.getNumero());
        alert.showAndWait();
    }

    public void eliminarPedido(Integer numero) {

        // comprobamos si existe pedido
        Pedido pedido = datos.getListaPedidos().existeNumeroPedido(numero);
        if( pedido == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No existe pedido para eliminar con número: "+numero);
            alert.showAndWait();
            return;
        }
        // comprobamos si no ha sido enviado
        if( pedido.pedidoEnviado() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El pedido ya ha sido enviado, Pedido con número: "+numero);
            alert.showAndWait();
            return;
        }

        LocalDateTime pedidoDate = pedido.getFechaHora();
        LocalDateTime now = LocalDateTime.now();
        // si la fecha actual es mayor que la fecha del pedido + 2 days
        if( now.isAfter(pedidoDate.plusDays(2)) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El pedido no puede eliminarse porque fue creado hace más de 2 días: "+numero);
            alert.showAndWait();
            return;
        }

        // borramos pedido
        datos.getListaPedidos().borrar(pedido);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText("Pedido eliminado con numero: " + numero);
        alert.showAndWait();
    }
}