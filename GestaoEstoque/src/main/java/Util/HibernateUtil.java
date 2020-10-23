package Util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import Model.Deposito;
import Model.Entrada_Saida;
import Model.Filial;
import Model.Fornecedor_Cliente;
import Model.Funcionario;
import Model.Nota_Fiscal;
import Model.Perecivel;
import Model.Produto;
import Model.Produto_Deposito;
import Model.Produto_Entrada_Saida;
import Model.Produto_Nota_Fiscal;
import Model.Risco;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	//método de construção do singleton
	public static SessionFactory getSessionFactory() {
		//SINGLETON -> SE ESTIVER VAZIO CRIA UMA NOVA CONEXÃO
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Configuração Hibernate equivalente a hibernate.cfg.xml
				Properties settings = new Properties();
				//settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				//settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_exemplo?useTimezone=true&serverTimezone=UTC");
				settings.put(Environment.URL, "jdbc:mysql://localhost/Gestao_Estoque?createDatabaseIfNotExist=true&useSSL=false&user=root&password=Hadouken&serverTimezone=UTC");
				//"jdbc:mysql://"+Server+"/"+dbName + "?useSSL=false&user="+userName+"&password="+pws);
				//useSSL=false
				//settings.put(Environment.USER, "root");
				//settings.put(Environment.PASS, "12345678");
				//settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Deposito.class);
				configuration.addAnnotatedClass(Filial.class);
				configuration.addAnnotatedClass(Fornecedor_Cliente.class);
				configuration.addAnnotatedClass(Funcionario.class);
				configuration.addAnnotatedClass(Nota_Fiscal.class);				
				configuration.addAnnotatedClass(Produto.class);
				//configuration.addAnnotatedClass(Produto_Entrada_Saida.class);
				//configuration.addAnnotatedClass(Produto_Deposito.class);
				//configuration.addAnnotatedClass(Produto_Nota_Fiscal.class);
				configuration.addAnnotatedClass(Entrada_Saida.class);
				//configuration.addAnnotatedClass(Risco.class);
				//configuration.addAnnotatedClass(Perecivel.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
