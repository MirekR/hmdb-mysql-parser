CREATE DATABASE metabolomics IF NOT EXISTS;

USE metabolomics;

create table t_references (
  parent_id varchar(255), 
  id text, 
  parent_tag text, 
  pubmed_id varchar(255), 
  reference_text text
 ); 


create table t_pathways (
  parent_id varchar(255), 
  id text, 
  parent_tag text, 
  name text, 
  smpdb_id varchar(255),
  kegg_map_id text
);

create table t_diseases (
  omim_id varchar(255),
  name text
);

create table t_metabolites (
  parent_id varchar(255), 
  id text, 
  parent_tag text, 
  iupac_name text, 
  inchikey text, 
  traditional_iupac text, 
  smiles text, 
  description text, 
  accession text, 
  nugowiki text, 
  foodb_id varchar(255), 
  pubchem_compound_id varchar(255), 
  bigg_id varchar(255), 
  state text, 
  biocyc_id varchar(255), 
  wikipidia text, 
  inchi text, 
  chemspider_id varchar(255), 
  synthesis_reference text, 
  creation_date text, 
  version text, 
  update_date text, 
  cs_description text, 
  kegg_id varchar(255), 
  monisotopic_molecular_weight text, 
  metlin_id varchar(255), 
  name text, 
  cas_registry_number text, 
  chemical_formula text, 
  average_molecular_weight text, 
  het_id varchar(255), 
  status text, 
  chebi_id varchar(255),
  knapsack_id varchar(255),
  drugbank_id varchar(255),
  metagene text(255),
  phenol_explorer_compound_id varchar(255),
  phenol_explorer_metabolite_id varchar(255),
  drugbank_metabolite_id text
 );
