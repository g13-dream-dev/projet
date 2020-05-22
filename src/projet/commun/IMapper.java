package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Competition;
import projet.data.Compte;
import projet.data.Coureur;
import projet.data.Course;
import projet.data.Memo;
import projet.data.Permis;
import projet.data.Personne;
import projet.data.Plat;
import projet.data.Service;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Personne update( @MappingTarget Personne target, Personne source );
	Coureur update( @MappingTarget Coureur target, Coureur source );
	
	Permis update( @MappingTarget Permis target, Permis source );
	@Mapping( target="permis", expression="java( source.getPermis() )" )
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	Memo update( @MappingTarget Memo target, Memo source );
	Service update( @MappingTarget Service target, Service source );
	
	Course update( @MappingTarget Course target, Course source );
	@Mapping( target="courses", expression="java( source.getCourses() )" )
	Competition update( @MappingTarget Competition target, Competition source );
	
	Plat update( @MappingTarget Plat target, Plat source );
	
}
