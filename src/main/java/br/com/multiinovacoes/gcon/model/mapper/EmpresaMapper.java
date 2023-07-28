package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Empresa;
import br.com.multiinovacoes.gcon.model.dto.EmpresaDto;
import br.com.multiinovacoes.gcon.model.request.EmpresaRequest;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
	
	
	List<EmpresaDto> fromResponseList(List<Empresa> list);
	
	@Mapping(target = "idOrgao", ignore = true)
	EmpresaDto toDto(Empresa empresa);
	 
	@Mapping(target = "id", ignore = true)
	EmpresaDto fromEmpresa(EmpresaRequest request);
	
	@Mapping(target = "orgao", ignore = true)
	Empresa toEmpresa(EmpresaDto dto);


}
