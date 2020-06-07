package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Competition;
import projet.data.Compte;
import projet.data.Coureur;
import projet.data.Course;
import projet.data.Materiel;
import projet.data.Permis;
import projet.data.Personne;
import projet.data.Plat;
import projet.data.Poste;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Personne update( @MappingTarget Personne target, Personne source );
	Coureur update( @MappingTarget Coureur target, Coureur source );
	
	Permis update( @MappingTarget Permis target, Permis source );
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	Course update( @MappingTarget Course target, Course source );
	Competition update( @MappingTarget Competition target, Competition source );
	
	Poste update( @MappingTarget Poste target, Poste source ); 
	
	Plat update( @MappingTarget Plat target, Plat source );
	
	Materiel update( @MappingTarget Materiel target, Materiel source );
	
}
