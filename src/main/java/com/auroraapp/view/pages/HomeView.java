    package com.auroraapp.view.pages;

    import com.auroraapp.model.Categoria;
    import com.auroraapp.model.Evento;    import com.auroraapp.model.Participante;
import com.auroraapp.model.Usuario;
import com.auroraapp.view.Router;
    import com.auroraapp.view.components.ContentList;
    import com.auroraapp.view.components.FilterSidebar;
    import com.auroraapp.view.components.HeaderBar;
    import com.auroraapp.view.components.SearchHeader;
    import com.auroraapp.view.components.EventoForm;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.collections.transformation.FilteredList;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.layout.Background;
    import javafx.scene.layout.BackgroundFill;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.CornerRadii;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.Priority;
    import javafx.scene.layout.VBox;
    import javafx.scene.paint.Color;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.stage.Window;

    import java.time.LocalDate;
    import java.util.LinkedList;

    public class HomeView extends BorderPane {
        public HomeView(Router router) {
            // Header
            HeaderBar header = new HeaderBar();
            setTop(header);

            // Filtros Lateral
            FilterSidebar sidebar = new FilterSidebar();
            setLeft(sidebar);

            // Conteudo Central ((Pesquisa + botao) e Lista)
            VBox center = new VBox();
            center.setBackground(new Background(
                new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)
            ));
            center.setPadding(new Insets(14, 14, 0, 14));
            center.setSpacing(8);

            HBox tableHeader = new HBox();
            SearchHeader search = new SearchHeader();

            // make search grow to push the button to the right (like flexbox space-between)
            tableHeader.setAlignment(Pos.CENTER_LEFT);
            HBox.setHgrow(search, Priority.ALWAYS);

            Button criarEventobotao = new Button("Criar Evento");
            criarEventobotao.setStyle(
                "-fx-background-color: #3b82f6; " +
                "-fx-text-fill: white; " +          
                "-fx-background-radius: 24px; " +    
                "-fx-padding: 8px 32px; " +          
                "-fx-font-weight: bold;"
            );
            criarEventobotao.setOnMouseEntered(e -> criarEventobotao.setStyle(
                "-fx-background-color: #2563eb; " + 
                "-fx-text-fill: white; " +
                "-fx-background-radius: 24px; " +
                "-fx-padding: 8px 32px; " +
                "-fx-font-weight: bold;"
            ));
            criarEventobotao.setOnMouseExited(e -> criarEventobotao.setStyle(
                "-fx-background-color: #3b82f6; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 24px; " +
                "-fx-padding: 8px 32px; " +
                "-fx-font-weight: bold;"
            ));

            // Open modal with EventoForm when clicked
            criarEventobotao.setOnAction(evt -> {
                EventoForm form = new EventoForm();
                Scene dialogScene = new Scene(form);
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);

                Window owner = this.getScene() != null ? this.getScene().getWindow() : null;
                if (owner != null) dialog.initOwner(owner);

                dialog.setTitle("Criar Evento");
                dialog.setScene(dialogScene);
                dialog.setResizable(false);
                dialog.showAndWait();
            });

            tableHeader.getChildren().addAll(search, criarEventobotao);
            tableHeader.alignmentProperty().set(Pos.CENTER_RIGHT);

            ContentList content = new ContentList();

            ObservableList<Evento> eventos = FXCollections.observableArrayList();

            // Eventos de exemplo (Futura integração com a API)
            eventos.add(new Evento(
                1,
                "Feira de Tecnologia",
                LocalDate.of(2025, 11, 10),
                LocalDate.of(2025, 11, 12),
                50.0,
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
            ));

            eventos.add(new Evento(
                2,
                "Concerto ao Ar Livre",
                LocalDate.of(2025, 12, 5),
                LocalDate.of(2025, 12, 5),
                120.0,
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
            ));
            
            
            LinkedList<Categoria> categoras = new LinkedList<>();
            categoras.add(new Categoria(0, "Fotografia"));

            LinkedList<Usuario> pessoas = new LinkedList<>();
            Participante teste = Participante
                .builder()
                .id(1)
                .nome("fd")
                .eventosPartcipados(new LinkedList<>())
                .build();
            pessoas.add(teste);

            eventos.add(new Evento(
                3,
                "Workshop de Fotografia",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 16),
                80.0,
                pessoas,
                new LinkedList<>(),
                categoras
            ));

            eventos.add(new Evento(
                3,
                "Workshop de Fotografia",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 16),
                80.0,
                new LinkedList<>(),
                new LinkedList<>(),
                categoras
            ));

            eventos.add(new Evento(
                3,
                "Workshop de Fotografia",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 16),
                80.0,
                new LinkedList<>(),
                new LinkedList<>(),
                categoras
            ));

            eventos.add(new Evento(
                3,
                "Workshop de Fotografia",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 16),
                80.0,
                new LinkedList<>(),
                new LinkedList<>(),
                categoras
            ));

            eventos.add(new Evento(
                3,
                "Workshop de Fotografia",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 16),
                80.0,
                new LinkedList<>(),
                new LinkedList<>(),
                categoras
            ));
            ObservableList<String> categorias = FXCollections.observableArrayList();

            // Categorias de exemplo (Futura integração com a API)
            categorias.add("TODAS");
            categorias.add("Musica");
            categorias.add("Fotografia");
            categorias.add("Carros");
            categorias.add("Educação");
            categorias.add("ASDLKJHSALK");

            if (!categorias.isEmpty()) {
                sidebar.setCategoryNames(categorias);
            }

            // Filtragem
            FilteredList<Evento> filtered = new FilteredList<>(eventos, e -> true);
            content.setEvents(filtered);

            Runnable updatePredicate = () -> {
                final String q = search.getText() == null ? "" : search.getText().trim().toLowerCase();
                final ObservableList<String> selectedCats = sidebar.getSelectedCategoryNames();

                filtered.setPredicate(ev -> {
                    // name filter
                    boolean nameMatches = q.isEmpty() || (ev.getNome() != null && ev.getNome().toLowerCase().contains(q));

                    if (selectedCats == null || selectedCats.isEmpty() || selectedCats.contains("TODAS")) {
                        return nameMatches;
                    }

                    boolean categoryMatches = ev.getCategorias().stream()
                        .map(cat -> {
                            if (cat instanceof Categoria) {
                                return ((Categoria) cat).getNome();
                            }
                            try { return (String) cat.getClass().getMethod("getNome").invoke(cat); }
                            catch (Exception ex) { return cat.toString(); }
                        })
                        .anyMatch(selectedCats::contains);

                    return nameMatches && categoryMatches;
                });
            };

            // Listeners
            search.textProperty().addListener((obs, o, n) -> updatePredicate.run());
            sidebar.getSelectedCategoryNames().addListener((javafx.collections.ListChangeListener<String>) c -> updatePredicate.run());

            VBox.setVgrow(content, Priority.ALWAYS);
            center.getChildren().addAll(tableHeader, content);

            HBox centerWrapper = new HBox(center);
            centerWrapper.setStyle("-fx-padding: 18;");
            setCenter(centerWrapper);

            setMargin(centerWrapper, new Insets(0, 20, 0, 20));
        }
    }