package com.taxi.database;

import com.taxi.entities.ConceptosGastosEntity;
import com.taxi.entities.TiposGastosEntity;
import com.taxi.pojos.ConceptosGastos;
import com.taxi.singletons.TaxiLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taxi.utils.FilterUtils.getFilterFieldValue;
import static com.taxi.utils.FilterUtils.matchModeTranslation;


@Stateless(name = ConceptosGastosDbBean.BEAN_NAME)
@Interceptors(TaxiLogger.class)
public class ConceptosGastosDbBean {

    public final static String BEAN_NAME = "ConceptosGastosDbBean";

    @Inject
    private Logger logger;

    @Inject
    private TiposGastoDbBean tiposGastoDbBean;

    @PersistenceContext
    private EntityManager em;

    public List<ConceptosGastosEntity> getData(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        StringBuilder rawQuery = new StringBuilder("SELECT * FROM conceptos_gastos, tipos_gastos WHERE conceptos_gastos.id_tipo_gasto = tipos_gastos.id");

        Query query = buildFilters(sortMeta, filterMeta, rawQuery, ConceptosGastosEntity.class);

        if (pageSize > 0)
            query = query.setMaxResults(pageSize).setFirstResult(first);

        return query.getResultList();
    }

    public int getTotal(Map<String, FilterMeta> filterMeta) {
        StringBuilder rawQuery = new StringBuilder("SELECT COUNT(*) FROM conceptos_gastos, tipos_gastos WHERE conceptos_gastos.id_tipo_gasto = tipos_gastos.id ");

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

                    if (entry.getKey().equalsIgnoreCase("tipoGasto.nombre")) {
                        rawQuery.append(" AND ").append(" tipos_gastos.nombre ").append(matchModeTranslation(entry.getValue().getFilterMatchMode())).append(":").append(entry.getKey());
                    } else {
                        rawQuery.append(" AND ").append("conceptos_gastos.").append(entry.getKey()).append(" LIKE ").append(":").append(entry.getKey());
                    }


                    parameters.put(entry.getKey(), entry.getValue());
                }
            }
        }

        if (sortMeta != null && !sortMeta.isEmpty()) {
            SortMeta sort = sortMeta.entrySet().iterator().next().getValue();
            if (sort.getSortField().equals("tipoGasto.nombre")) {
                rawQuery.append(" ORDER BY ").append(" tipos_gastos.nombre ").append(" ").append(sort.getSortOrder() == SortOrder.DESCENDING ? " DESC " : " ASC ");
            } else {
                rawQuery.append(" ORDER BY ").append(" conceptos_gastos. ").append(sort.getSortField()).append(" ").append(sort.getSortOrder() == SortOrder.DESCENDING ? " DESC " : " ASC ");
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

    public void update(ConceptosGastos conceptosGastos) {
        ConceptosGastosEntity conceptosGastosEntity = em.find(ConceptosGastosEntity.class, conceptosGastos.getId());
        TiposGastosEntity tiposGastosEntity = tiposGastoDbBean.findSingleByName(conceptosGastos.getTipoGasto().getNombre());
        conceptosGastosEntity.setNombre(conceptosGastos.getNombre());
        conceptosGastosEntity.setTiposGastosEntity(tiposGastosEntity);

        em.merge(conceptosGastosEntity);
    }

    public void insert(ConceptosGastos conceptosGastos) {
        em.persist(new ConceptosGastosEntity(conceptosGastos));
    }

    public void delete(Long id) {
        em.remove(em.find(ConceptosGastosEntity.class, id));
    }

    public List<ConceptosGastosEntity> findByName(String value) {
        Query query = em.createNativeQuery("SELECT * FROM conceptos_gastos WHERE nombre LIKE '" + value + "%'  ORDER BY nombre ",
                ConceptosGastosEntity.class);

        return query.setMaxResults(10).getResultList();
    }

    public ConceptosGastosEntity findSingleByName(String value) {
        return findByName(value).get(0);
    }

    public ConceptosGastosEntity findById(Long id) {
        return em.find(ConceptosGastosEntity.class, id);
    }
}
