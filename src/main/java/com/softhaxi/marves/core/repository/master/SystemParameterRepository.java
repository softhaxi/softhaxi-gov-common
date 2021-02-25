package com.softhaxi.marves.core.repository.master;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.master.SystemParameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface SystemParameterRepository extends JpaRepository<SystemParameter, UUID> {
    @Query("FROM SystemParameter WHERE UPPER(code) = UPPER(?1)")
    public Optional<SystemParameter> findByCode(String code);

    @Query("FROM SystemParameter WHERE code IN (?1)")
    public Collection<SystemParameter> findByCodes(Collection<String> codes);

    @Query("FROM SystemParameter WHERE UPPER(code) like UPPER(?1)")//TODO add like %%
    public Collection<SystemParameter> findSysParamByCode(String code);

    @Query("FROM SystemParameter WHERE isDeleted = false Order by code")
    public List<SystemParameter> findAll();

}
