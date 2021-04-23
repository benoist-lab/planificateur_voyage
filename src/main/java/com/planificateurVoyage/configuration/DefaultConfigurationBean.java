package com.planificateurVoyage.configuration;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;



public class DefaultConfigurationBean implements BeanDefinitionRegistryPostProcessor {

	private Class classe;
	private String nomBean;
	private String initMethode;
	
	private Map<String,Object> properties=null;

	
	public DefaultConfigurationBean (Class classe,String nomBean,String initMethode) {
		this.classe=classe;
		this.nomBean=nomBean;
		this.initMethode=initMethode;
	}
	
	
	public DefaultConfigurationBean (Class classe,String nomBean,String initMethode, Map<String,Object> properties) {
		this(classe,nomBean,initMethode);
		this.properties=properties;
	}	

	  @Override
	  public void postProcessBeanDefinitionRegistry (BeanDefinitionRegistry registry)
	            throws BeansException {

	      GenericBeanDefinition bd = new GenericBeanDefinition();
	      bd.setBeanClass(classe);
	      
	      bd.setInitMethodName(initMethode);

	      if (properties!=null) {
	    	  for (Map.Entry<String, Object> entry:properties.entrySet()) {
	    		  bd.getPropertyValues().add(entry.getKey(), entry.getValue());
	    	  }
	      }
	      
	      registry.registerBeanDefinition(nomBean, bd);
	  }

	  @Override
	  public void postProcessBeanFactory (ConfigurableListableBeanFactory beanFactory)
	            throws BeansException {
	      //no op
	  }
}
