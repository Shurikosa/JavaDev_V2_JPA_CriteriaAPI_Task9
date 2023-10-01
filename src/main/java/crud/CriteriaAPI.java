package crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.log4j.Logger;
import entities.*;

import java.util.List;

public class CriteriaAPI {

    private CriteriaAPI() {
    }

    private static final Logger LOGGER = Logger.getLogger(CriteriaAPI.class);

    public static List<Object[]> ownersProcessWithCriteriaAPI(EntityManager entityManager) {
        LOGGER.info("Start query");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Resident> residentRoot = query.from(Resident.class);

        Join<Resident,Member> osbbMemberJoin = residentRoot.join("memberId", JoinType.INNER);
        Join<Resident, Flat> flatJoin = residentRoot.join("flatId", JoinType.INNER);
        Join<Flat, Building> buildingJoin = flatJoin.join("buildingId",JoinType.INNER);

        Predicate carAccessNotAllowed = criteriaBuilder.equal(residentRoot.get("carAccess"), false);

        query.multiselect(
                osbbMemberJoin.get("firstName"),
                osbbMemberJoin.get("lastName"),
                osbbMemberJoin.get("email"),
                buildingJoin.get("adress"),
                flatJoin.get("number"),
                flatJoin.get("area")
        );

        query.where(criteriaBuilder.and(carAccessNotAllowed));

        List<Object[]> resultList = entityManager.createQuery(query).getResultList();

        LOGGER.info("Return list");
        return resultList;
    }
}