package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CreditDAOImpl;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.ICreditDAO;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;

import java.util.List;

public class CreditController {

    private ICreditDAO creditDAO;

    private static CreditController instance;

    private CreditController() {
        creditDAO = CreditDAOImpl.getInstance();
    }

    public static CreditController getInstance() {
        if (instance == null) {
            instance = new CreditController();
        }
        return instance;
    }

    public Credit createCredit(Person person, CreditRole creditRole, Programme programme) {
        Credit credit = new Credit(person, creditRole);
        credit.setProgramme(programme);
        programme.getCredits().add(credit);
        creditDAO.save(credit);
        ProgrammeController.getInstance().updateProgramme(programme);
        return credit;
    }

    public void update(Credit creditObj) {
        creditDAO.update(creditObj);
    }

    public void delete(Credit credit) {
        Programme programme = credit.getProgramme();
        programme.getCredits().remove(credit);
        creditDAO.delete(credit);
        ProgrammeController.getInstance().updateProgramme(programme);
    }

    public List<Credit> getAll() {
        return creditDAO.getAll();
    }
}
