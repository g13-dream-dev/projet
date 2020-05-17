package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Coureur;
import projet.data.Memo;
import projet.data.Permis;
import projet.data.Personne;
import projet.data.Service;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	//Categorie update( @MappingTarget Categorie target, Categorie source );

	//@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );
	Coureur update( @MappingTarget Coureur target, Coureur source );
	
	Permis update( @MappingTarget Permis target, Permis source );
	@Mapping( target="permis", expression="java( source.getPermis() )" )
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	//@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Memo update( @MappingTarget Memo target, Memo source );
	Service update( @MappingTarget Service target, Service source );
	
}
