package uff.ic.lleme.tic10002.aulas._old.s20172.oo;

public class Empregado {

    public int matricula;
    public String nome;
    public String cargo;
    public float salario;

    public void aumentarSalario(float indice) {
        salario = salario * (1 + indice / 100);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
