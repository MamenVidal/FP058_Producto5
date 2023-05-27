package TheBigDev;

import TheBigDev.modelo.Articulo;
import TheBigDev.modelo.Cliente;
import TheBigDev.modelo.Pedido;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class TheBigDevApplication extends Application {

    TheBigDevController controller = new TheBigDevController();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("TheBigDev App");

        // maximizar
        stage.setMaximized(true);

        // Layout principal
        VBox mainLayout = new VBox();
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);

        // Bloques de gestión
        Label tituloArticulos = new Label("Gestión de Artículos");
        tituloArticulos.setFont(new Font("Arial", 20));
        GridPane gestionArticulos = createGestionArticulos();

        Label tituloClientes = new Label("Gestión de Clientes");
        tituloClientes.setFont(new Font("Arial", 20));
        GridPane gestionClientes = createGestionClientes();

        Label tituloPedidos = new Label("Gestión de Pedidos");
        tituloPedidos.setFont(new Font("Arial", 20));
        GridPane gestionPedidos = createGestionPedidos();

        // Botón para salir
        Button btnSalir = new Button("Salir");
        btnSalir.setFont(new Font("Arial", 16));
        btnSalir.setOnAction(e -> stage.close());

        // Agregar todo al layout principal
        mainLayout.getChildren().addAll(
            tituloArticulos,
            gestionArticulos,
            tituloClientes,
            gestionClientes,
            tituloPedidos,
            gestionPedidos,
            btnSalir
        );

        // Crear y mostrar la escena
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane createGestionArticulos() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // creamos botón y funcionalidad para añadir articulo
        Button btnAddArticulo = new Button("Añadir Artículo");
        btnAddArticulo.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Añadir Artículo");

            TextField txtCodigo = new TextField();
            TextField txtDescripcion = new TextField();
            TextField txtPrecioVenta = new TextField();
            TextField txtGastoEnvio = new TextField();
            TextField txtTiempo = new TextField();

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setOnAction(ev -> {
                try {
                    String codigo = txtCodigo.getText();
                    String descripcion = txtDescripcion.getText();
                    float precioVenta = Float.parseFloat(txtPrecioVenta.getText());
                    float gastoEnvio = Float.parseFloat(txtGastoEnvio.getText());
                    int tiempo = Integer.parseInt(txtTiempo.getText());

                    controller.addArticulo(codigo, descripcion, precioVenta, gastoEnvio, tiempo);
                } catch (Exception exc) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al agregar el artículo: " + exc.getMessage());
                    alert.showAndWait();
                }
            });

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_add -> stage.close());

            VBox layout = new VBox(10,
                    new Label("Código:"), txtCodigo,
                    new Label("Descripción:"), txtDescripcion,
                    new Label("Precio Venta:"), txtPrecioVenta,
                    new Label("Gasto de Envío:"), txtGastoEnvio,
                    new Label("Tiempo de Preparación:"), txtTiempo,
                    btnGuardar,
                    btnSalir
            );
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // creamos botón y funcionalidad para mostrar articulos
        Button btnMostrarArticulos = new Button("Mostrar Artículos");
        btnMostrarArticulos.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Artículos");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Articulo> articulos = controller.datos.getListaArticulos().getArrayList();
            for (Articulo articulo : articulos) {
                // Añadimos cada artículo al listView
                listView.getItems().add(articulo.toString());
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // Aquí puedes agregar los eventos de los botones...

        grid.add(btnAddArticulo, 0, 0);
        grid.add(btnMostrarArticulos, 0, 1);

        return grid;
    }

    private GridPane createGestionClientes() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // creamos botón y funcionalidad para añadir cliente
        Button btnAddCliente = new Button("Añadir Cliente");
        btnAddCliente.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Añadir Cliente");

            TextField txtClienteEmail = new TextField();
            TextField txtClienteNif = new TextField();
            TextField txtClienteNombre = new TextField();
            TextField txtClienteDomicilio = new TextField();
            TextField txtPremium = new TextField();

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setOnAction(ev -> {
                try {
                    String email = txtClienteEmail.getText();
                    String nif = txtClienteNif.getText();
                    String nombre = txtClienteNombre.getText();
                    String domicilio = txtClienteDomicilio.getText();
                    Boolean premium = Boolean.parseBoolean(txtPremium.getText());

                    controller.addCliente(email,nif,nombre,domicilio, premium) ;
                } catch (Exception exc) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al agregar el artículo: " + exc.getMessage());
                    alert.showAndWait();
                }
            });

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_cliente_add -> stage.close());

            VBox layout = new VBox(10,
                    new Label("Email:"), txtClienteEmail,
                    new Label("NIF:"), txtClienteNif,
                    new Label("Nombre:"), txtClienteNombre,
                    new Label("Domicilio:"), txtClienteDomicilio,
                    new Label("Es premium (true / false):"), txtPremium,
                    btnGuardar,
                    btnSalir
            );
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // creamos botón y funcionalidad para mostrar todos los clientes
        Button btnMostrarClientes = new Button("Mostrar Clientes");
        btnMostrarClientes.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Clientes");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Cliente> clientes = controller.datos.getListaClientes().getArrayList();
            for (Cliente cliente : clientes) {
                // Añadimos cada artículo al listView
                listView.getItems().add(cliente.toString());
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // creamos botón y funcionalidad para mostrar clientes estándar
        Button btnMostrarClientesEstandar = new Button("Mostrar Clientes Estándar");
        btnMostrarClientesEstandar.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Clientes Estándar");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Cliente> clientes = controller.datos.getListaClientes().getArrayList();
            for (int i = 0; i < clientes.size(); i++) {
                // sólo imprimimos el cliente si es estándar
                if( clientes.get(i).tipoCliente() == "estandar" ) {
                    // Añadimos cada artículo al listView
                    listView.getItems().add(clientes.get(i).toString());
                }
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // creamos botón y funcionalidad para mostrar clientes premium
        Button btnMostrarClientesPremium = new Button("Mostrar Clientes Premium");
        btnMostrarClientesPremium.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Clientes Premium");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Cliente> clientes = controller.datos.getListaClientes().getArrayList();
            for (int i = 0; i < clientes.size(); i++) {
                // sólo imprimimos el cliente si es estándar
                if( clientes.get(i).tipoCliente() == "premium" ) {
                    // Añadimos cada artículo al listView
                    listView.getItems().add(clientes.get(i).toString());
                }
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });

        // Aquí puedes agregar los eventos de los botones...

        grid.add(btnAddCliente, 0, 0);
        grid.add(btnMostrarClientes, 0, 1);
        grid.add(btnMostrarClientesEstandar, 0, 2);
        grid.add(btnMostrarClientesPremium, 0, 3);

        return grid;
    }

    private GridPane createGestionPedidos() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // creamos botón y funcionalidad para añadir pedido
        Button btnAddPedido = new Button("Añadir Pedido");
        btnAddPedido.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Añadir Pedido");

            TextField txtPedidoNumero = new TextField();
            TextField txtPedidoEmail = new TextField();
            TextField txtPedidoCodigo = new TextField();
            TextField txtPedidoCantidad = new TextField();
            TextField txtPedidoEnviado = new TextField();

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setOnAction(ev -> {
                try {
                    Integer numero = Integer.parseInt(txtPedidoNumero.getText());
                    String email = txtPedidoEmail.getText();
                    String codigo = txtPedidoCodigo.getText();
                    Integer cantidad = Integer.parseInt(txtPedidoCantidad.getText());
                    Boolean enviado = Boolean.parseBoolean(txtPedidoEnviado.getText());

                    controller.addPedido(numero,email,codigo,cantidad,enviado) ;
                } catch (Exception exc) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al agregar el pedido: " + exc.getMessage());
                    alert.showAndWait();
                }
            });

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_cliente_add -> stage.close());

            VBox layout = new VBox(10,
                    new Label("Número de Pedido:"), txtPedidoNumero,
                    new Label("Email del Cliente:"), txtPedidoEmail,
                    new Label("Código de Artículo:"), txtPedidoCodigo,
                    new Label("Cantidad:"), txtPedidoCantidad,
                    new Label("Esta enviado (true / false):"), txtPedidoEnviado,
                    btnGuardar,
                    btnSalir
            );
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });


        // creamos botón y funcionalidad para eliminar pedido
        Button btnEliminarPedido = new Button("Eliminar Pedido");
        btnEliminarPedido.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Eliminar Pedido");

            TextField txtPedidoNumeroAEliminar = new TextField();

            Button btnGuardar = new Button("Eliminar");
            btnGuardar.setOnAction(ev -> {
                try {
                    Integer numero = Integer.parseInt(txtPedidoNumeroAEliminar.getText());
                    controller.eliminarPedido(numero);
                } catch (Exception exc) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error al eliminar el pedido: " + exc.getMessage());
                    alert.showAndWait();
                }
            });

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_cliente_add -> stage.close());

            VBox layout = new VBox(10,
                    new Label("Número de Pedido a eliminar:"), txtPedidoNumeroAEliminar,
                    btnGuardar,
                    btnSalir
            );
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });



        // creamos botón y funcionalidad para mostrar pedidos pendientes
        Button btnMostrarPedidosPendientesEnvio = new Button("Mostrar Pedidos Pendientes Envío");
        btnMostrarPedidosPendientesEnvio.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Pedidos pendientes de envío");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Pedido> pedidos = controller.datos.getListaPedidos().getArrayList();
            for (int i = 0; i < pedidos.size(); i++) {
                if( !pedidos.get(i).getEnviado() ) {
                    listView.getItems().add(pedidos.get(i).toString());
                }
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });


        // creamos botón y funcionalidad para mostrar pedidos enviados
        Button btnMostrarPedidosEnviados = new Button("Mostrar Pedidos Enviados");
        btnMostrarPedidosEnviados.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Pedidos enviados");

            ListView<String> listView = new ListView<>();

            // Obtenemos los artículos desde el controlador
            List<Pedido> pedidos = controller.datos.getListaPedidos().getArrayList();
            for (int i = 0; i < pedidos.size(); i++) {
                if( pedidos.get(i).getEnviado() ) {
                    listView.getItems().add(pedidos.get(i).toString());
                }
            }

            // Botón para salir
            Button btnSalir = new Button("Salir");
            btnSalir.setOnAction(e_articulo_show -> stage.close());

            VBox layout = new VBox(10, listView, btnSalir);
            layout.setPadding(new Insets(15));

            stage.setScene(new Scene(layout));
            stage.show();
        });



        // Aquí puedes agregar los eventos de los botones...

        grid.add(btnAddPedido, 0, 0);
        grid.add(btnEliminarPedido, 0, 1);
        grid.add(btnMostrarPedidosPendientesEnvio, 0, 2);
        grid.add(btnMostrarPedidosEnviados, 0, 3);

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}