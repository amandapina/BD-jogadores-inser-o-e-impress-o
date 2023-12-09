import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Configurar o Hibernate
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Jogador.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Adicionar jogadores
        adicionarJogadores(sessionFactory);

        // Consultar e imprimir os dados dos jogadores
        consultarEImprimir(sessionFactory);

        // Fechar a sessão do Hibernate
        sessionFactory.close();
    }

    private static void adicionarJogadores(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Jogador jogador1 = new Jogador();
            jogador1.setNome("Jogador1");
            jogador1.setFuncao("Atirador");
            jogador1.setTime("Time A");
            jogador1.setPatente("Mestre");

            Jogador jogador2 = new Jogador();
            jogador2.setNome("Jogador2");
            jogador2.setFuncao("Suporte");
            jogador2.setTime("Time B");
            jogador2.setPatente("Ouro");

            Jogador jogador3 = new Jogador();
            jogador3.setNome("Jogador3");
            jogador3.setFuncao("Tanque");
            jogador3.setTime("Time C");
            jogador3.setPatente("Diamante");

            session.save(jogador1);
            session.save(jogador2);
            session.save(jogador3);

            session.getTransaction().commit();
        }
    }

    private static void consultarEImprimir(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Consultar jogadores
            TypedQuery<Jogador> query = session.createQuery("FROM Jogador", Jogador.class);
            for (Jogador jogador : query.getResultList()) {
                // Imprimir dados do jogador
                System.out.println("ID: " + jogador.getId());
                System.out.println("Nome: " + jogador.getNome());
                System.out.println("Função: " + jogador.getFuncao());
                System.out.println("Time: " + jogador.getTime());
                System.out.println("Patente: " + jogador.getPatente());
                System.out.println("--------------");
            }

            session.getTransaction().commit();
        }
    }
}
