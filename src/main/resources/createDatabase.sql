create table references (
  parent_id varchar(255), 
  id varchar(255), 
  parent_tag varchar(255), 
  pubmed_id varchar(255), 
  reference_text text
 ); 


create table pathways (
  parent_id varchar(255), 
  id varchar(255), 
  parent_tag varchar(255), 
  name varchar(255), 
  smpdb_id varchar(255)
);


create table metabolites (
  parent_id varchar(255), 
  id varchar(255), 
  parent_tag varchar(255), 
  iupac_name varchar(255), 
  inchikey varchar(255), 
  traditional_iupac varchar(255), 
  smiles varchar(255), 
  description text, 
  accession varchar(255), 
  nugowiki varchar(255), 
  foodb_id varchar(255), 
  pubchem_compound_id varchar(255), 
  bigg_id varchar(255), 
  state varchar(255), 
  biocyc_id varchar(255), 
  wikipidia text, 
  inchi varchar(255), 
  chemspider_id varchar(255), 
  synthesis_reference varchar(255), 
  creation_date varchar(255), 
  version varchar(255), 
  update_date varchar(255), 
  cs_description varchar(255), 
  kegg_id varchar(255), 
  monisotopic_molecular_weight varchar(255), 
  metlin_id varchar(255), 
  name varchar(255), 
  cas_registry_number varchar(255), 
  chemical_formula varchar(255), 
  average_molecular_weight varchar(255), 
  het_id varchar(255), 
  status varchar(255), 
  chebi_id varchar(255)
 );
