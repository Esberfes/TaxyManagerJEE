package database;

import entities.LicenciasEntity;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import pojos.Licencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.FilterUtils.getFilterFieldValue;

@Stateless(name = LicenciasDbBean.BEAN_NAME)
public class LicenciasDbBean {

    public final static String BEAN_NAME = "LicenciasDbBean";

    @PersistenceContext
    private EntityManager em;

    public List<LicenciasEntity> getData(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        StringBuilder rawQuery = new StringBuilder("SELECT * FROM licencias, empresas WHERE licencias.id_empresa = empresas.id ");

        Query query = buildFilters(sortMeta, filterMeta, rawQuery, LicenciasEntity.class);

        if (pageSize > 0)
            query = query.setMaxResults(pageSize).setFirstResult(first);

        return query.getResultList();
    }

    public int getTotal(Map<String, FilterMeta> filterMeta) {
        StringBuilder rawQuery = new StringBuilder("SELECT COUNT(*) FROM licencias, empresas WHERE licencias.id_empresa = empresas.id ");

        Query query = buildFilters(null, filterMeta, rawQuery, null);

        return ((BigInteger) query.getSingleResult()).intValue();
    }

    private Query buildFilters(Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta, StringBuilder rawQuery, Class clazz) {
        Map<String, FilterMeta> parameters = new HashMap<>();

        if (filterMeta != null) {
            for (Map.Entry<String, FilterMeta> entry : filterMeta.entrySet()) {
                if (entry.getValue().getFilterValue() != null
                        && StringUtils.isNotBlank(String.valueOf(entry.getValue().getFilterValue()))
                        && entry.getValue().getFilterValue() != null
                        && StringUtils.isNotBlank(String.valueOf(entry.getValue().getFilterValue()))) {

                    if (entry.getKey().equalsIgnoreCase("empresa.nombre")) {
                        rawQuery.append(" AND ").append(" empresas.nombre ").append("LIKE ").append(":").append(entry.getKey());
                    } else {
                        rawQuery.append(" AND ").append("licencias.").append(entry.getKey()).append(" LIKE ").append(":").append(entry.getKey());
                    }

                    parameters.put(entry.getKey(), entry.getValue());
                }
            }
        }

        if (sortMeta != null && !sortMeta.isEmpty()) {
            SortMeta sort = sortMeta.entrySet().iterator().next().getValue();
            if (sort.getSortField().equals("empresa.nombre")) {
                rawQuery.append(" ORDER BY ").append("empresas.nombre").append(" ").append(sort.getSortOrder() == SortOrder.DESCENDING ? " DESC " : " ASC ");
            } else {
                rawQuery.append(" ORDER BY ").append("conductores.").append(sort.getSortField()).append(" ").append(sort.getSortOrder() == SortOrder.DESCENDING ? " DESC " : " ASC ");
            }
        }

        Query query;
        if (clazz != null)
            query = em.createNativeQuery(rawQuery.toString(), clazz);
        else
            query = em.createNativeQuery(rawQuery.toString());

        for (Map.Entry<String, FilterMeta> parameter : parameters.entrySet())
            query.setParameter(parameter.getKey(), getFilterFieldValue(parameter.getValue()));

        return query;
    }

    public LicenciasEntity insert(Licencia licencia) {
        LicenciasEntity licenciasEntity = new LicenciasEntity(licencia);
        em.persist(licenciasEntity);

        return licenciasEntity;
    }


    public void truncate() {
        em.createNativeQuery("DELETE FROM licencias WHERE true").executeUpdate();
    }

    public void update(Licencia licencia) {
        LicenciasEntity licenciasEntity = em.find(LicenciasEntity.class, licencia.getId());
        licenciasEntity.setEsEurotaxi(licencia.getEs_eurotaxi());

        em.merge(licenciasEntity);
    }
}