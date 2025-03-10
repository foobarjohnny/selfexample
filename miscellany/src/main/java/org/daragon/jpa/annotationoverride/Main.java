package org.daragon.jpa.annotationoverride;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.daragon.jpa.annotationoverride.model.BadProfessor;
import org.daragon.jpa.annotationoverride.model.Professor;
import org.daragon.jpa.annotationoverride.service.ProfessorService;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		JPAUtil util = new JPAUtil();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("ProfessorService");
		EntityManager em = emf.createEntityManager();
		ProfessorService service = new ProfessorService(em);

		em.getTransaction().begin();

		BadProfessor emp2 = new BadProfessor();
		emp2.setId(2);

		emp2.setName("bad");

		service.createProfessor(emp2);

		System.out.println("Professors: ");
		for (Professor emp1 : service.findAllProfessors()) {
			System.out.print(emp1);
		}

		util.checkData("select * from BAD_EMP");

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
