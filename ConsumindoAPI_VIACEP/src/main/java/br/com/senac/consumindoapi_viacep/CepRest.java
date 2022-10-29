/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.consumindoapi_viacep;

import br.com.senac.entidade.Endereco;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 *
 * @author 1m4ru
 */
public class CepRest {

    private Client client;
    private WebResource webResource;

    public CepRest() {
        ClientConfig clientConfi = new DefaultClientConfig(GensonProvider.class);
        client = Client.create(clientConfi);
        client.addFilter(new LoggingFilter(System.out));
        webResource = client.resource("https://viacep.com.br/ws/");

    }

    public Endereco pesquisarCep(String cep) {

        return webResource.path(cep).path("/json").get(Endereco.class);

    }

    public static void main(String[] args) {
        CepRest cepRest = new CepRest();
        Endereco end = cepRest.pesquisarCep("88132-857");

        System.out.println("Log: " + end.getLogradouro());
        System.out.println("Bairro: " + end.getBairro());
        System.out.println("Cidade: " + end.getLocalidade());
        System.out.println("Cep: " + end.getCep());
        System.out.println("Estado: " + end.getUf());
        System.out.println("DDD: " + end.getDdd());

    }

}
