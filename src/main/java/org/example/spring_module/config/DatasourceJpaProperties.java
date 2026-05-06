package org.example.spring_module.config;


public class DatasourceJpaProperties {

  private String dialect;
  private String ddlAuto;
  private boolean showSql;

  public String getDialect()          { return dialect; }
  public String getDdlAuto()          { return ddlAuto; }
  public boolean isShowSql()          { return showSql; }

  public void setDialect(String d)    { this.dialect = d; }
  public void setDdlAuto(String d)    { this.ddlAuto = d; }
  public void setShowSql(boolean s)   { this.showSql = s; }
}