package uff.ic.lleme.tic10002.aulas.arvores.AVL;

import uff.ic.lleme.tic10002.aulas.utils.Empregado;

public class AVL {

    private No raiz = null;
    private int maxSaldo = 0;

    private class No {

        public Empregado conteudo = null;
        public No esquerda = null;
        public No direita = null;
        public int saldoAltura = 0;

        public No(Empregado conteudo) {
            this.conteudo = conteudo;
        }

        public void printTree() {
            if (esquerda != null)
                esquerda.printTree(false, "");
            printNodeValue();
            if (direita != null)
                direita.printTree(true, "");
        }

        private void printNodeValue() {
            System.out.print("" + conteudo.chave() + "/" + saldo());
            System.out.print('\n');
        }

        private void printTree(boolean isRight, String indent) {
            if (esquerda != null)
                esquerda.printTree(false, indent + (isRight ? " |      " : "        "));
            System.out.print(indent);
            if (isRight)
                System.out.print(" \\");
            else
                System.out.print(" /");
            System.out.print("----- ");
            printNodeValue();
            if (direita != null)
                direita.printTree(true, indent + (isRight ? "        " : " |      "));
        }

        private int altura() {
            return Math.max(altura(this.direita), altura(this.esquerda)) + 1;
        }

        private int altura(No no) {
            if (no != null)
                return Math.max(altura(no.direita), altura(no.esquerda)) + 1;
            return 0;
        }

        private int saldo() {
            if (this.direita != null && this.esquerda != null)
                return Math.abs(this.direita.altura() - this.esquerda.altura());
            else if (this.direita != null && this.esquerda == null)
                return Math.abs(this.direita.altura() - 0);
            else if (this.direita == null && this.esquerda != null)
                return Math.abs(0 - this.esquerda.altura());
            return 0;
        }
    }

    public void print() {
        raiz.printTree();
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(No no) {
        if (no != null)
            return Math.max(altura(no.direita), altura(no.esquerda)) + 1;
        return 0;
    }

    public int maxSaldo() {
        maxSaldo = 0;
        altura2(raiz);
        return maxSaldo;
    }

    private int altura2(No no) {
        if (no != null) {
            int alturaDireita = altura2(no.direita);
            int alturaEsquerda = altura2(no.esquerda);
            int saldoSubArvores = Math.abs(alturaDireita - alturaEsquerda);
            if (saldoSubArvores > maxSaldo)
                maxSaldo = saldoSubArvores;
            return Math.max(alturaDireita, alturaEsquerda) + 1;
        }
        return 0;
    }

    public boolean inserir(Empregado conteudo) {
        if (raiz == null)
            return (raiz = new No(conteudo)) != null;
        else
            return inserir(raiz, conteudo);

    }

    private boolean inserir(No no, Empregado conteudo) {
        if (no.conteudo.chave() == conteudo.chave())
            return false;
        else if (no.conteudo.chave() > conteudo.chave())
            if (no.direita == null) {
                no.direita = new No(conteudo);

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                no.saldoAltura++;
                //</editor-fold>

                return true;
            } else {

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                int saldoAnteriorU = no.direita.saldoAltura;
                int saldoAnteriorV = 0;
                if (no.direita.esquerda != null)
                    saldoAnteriorV = no.direita.esquerda.saldoAltura;
                //</editor-fold>

                boolean inserido = inserir(no.direita, conteudo);

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                int saldoPosteriorU = no.direita.saldoAltura;
                int saldoPosteriorV = 0;
                if (no.direita.esquerda != null)
                    saldoPosteriorV = no.direita.esquerda.saldoAltura;

                int delta1 = saldoPosteriorU - saldoAnteriorU;
                int delta2 = saldoPosteriorV - saldoAnteriorV;

                if (Math.abs(saldoPosteriorU) > Math.abs(saldoAnteriorU)) {
                    no.saldoAltura++;
                    if (Math.abs(no.saldoAltura) > 1)
                        balancearADireita(no, delta1, delta2);
                }
                //</editor-fold>

                return inserido;
            }
        else if (no.conteudo.chave() < conteudo.chave())
            if (no.esquerda == null) {
                no.esquerda = new No(conteudo);

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                no.saldoAltura--;
                //</editor-fold>

                return true;
            } else {

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                int saldoAnteriorZ = no.esquerda.saldoAltura;
                int saldoAnteriorY = 0;
                if (no.esquerda.direita != null)
                    saldoAnteriorY = no.esquerda.direita.saldoAltura;
                //</editor-fold>

                boolean inserido = inserir(no.esquerda, conteudo);

                //<editor-fold defaultstate="collapsed" desc="balanceamento">
                int saldoPosteriorZ = no.esquerda.saldoAltura;
                int saldoPosteriorY = 0;
                if (no.esquerda.direita != null)
                    saldoPosteriorY = no.esquerda.direita.saldoAltura;

                int delta1 = saldoPosteriorZ - saldoAnteriorZ;
                int delta2 = saldoPosteriorY - saldoAnteriorY;

                if (Math.abs(saldoPosteriorZ) > Math.abs(saldoAnteriorZ)) {
                    no.saldoAltura--;
                    if (Math.abs(no.saldoAltura) > 1)
                        balancearAEsquerda(no, delta1, delta2);
                }
                //</editor-fold>

                return inserido;
            }
        else
            return false;
    }

    private void balancearADireita(No n1, int delta1, int delta2) {
        if (delta1 > 0) {
            No n2 = n1.direita;
            Empregado p = n1.conteudo;
            Empregado u = n2.conteudo;
            No t1 = n2.direita;
            No t2 = n2.esquerda;
            No t3 = n1.esquerda;

            n1.conteudo = u;
            n1.direita = t1;
            n1.esquerda = n2;
            n2.conteudo = p;
            n2.direita = t2;
            n2.esquerda = t3;

            n1.saldoAltura = 0;
            n2.saldoAltura = 0;
        } else if (delta1 < 0) {
            No n2 = n1.direita;
            No n3 = n2.esquerda;
            Empregado p = n1.conteudo;
            Empregado u = n2.conteudo;
            Empregado v = n3.conteudo;
            No t1 = n2.direita;
            No t2 = n3.direita;
            No t3 = n3.esquerda;
            No t4 = n1.esquerda;

            n1.conteudo = v;
            n1.direita = n2;
            n1.esquerda = n3;
            n2.conteudo = u;
            n2.direita = t1;
            n2.esquerda = t2;
            n3.conteudo = p;
            n3.direita = t3;
            n3.esquerda = t4;

            n1.saldoAltura = 0;
            if (t2 == null && t3 == null) {
                n2.saldoAltura = 0;
                n3.saldoAltura = 0;
            } else if (delta2 > 0) {
                n2.saldoAltura = 0;
                n3.saldoAltura = -1;
            } else {
                n2.saldoAltura = 1;
                n3.saldoAltura = 0;
            }
        }
    }

    private void balancearAEsquerda(No n1, int delta, int delta2) {
        if (delta < 0) {
            No n2 = n1.esquerda;
            Empregado p = n1.conteudo;
            Empregado z = n2.conteudo;
            No t1 = n1.direita;
            No t2 = n2.direita;
            No t3 = n2.esquerda;

            n1.conteudo = z;
            n1.direita = n2;
            n1.esquerda = t3;
            n2.conteudo = p;
            n2.direita = t1;
            n2.esquerda = t2;

            n1.saldoAltura = 0;
            n2.saldoAltura = 0;
        } else if (delta > 0) {
            No n2 = n1.esquerda;
            No n3 = n2.direita;
            Empregado p = n1.conteudo;
            Empregado z = n2.conteudo;
            Empregado y = n3.conteudo;
            No t1 = n1.direita;
            No t2 = n3.direita;
            No t3 = n3.esquerda;
            No t4 = n2.esquerda;

            n1.conteudo = y;
            n1.direita = n2;
            n1.esquerda = n3;
            n2.conteudo = p;
            n2.direita = t1;
            n2.esquerda = t2;
            n3.conteudo = z;
            n3.direita = t3;
            n3.esquerda = t4;

            n1.saldoAltura = 0;
            if (t2 == null && t3 == null) {
                n2.saldoAltura = 0;
                n3.saldoAltura = 0;
            } else if (delta2 < 0) {
                n2.saldoAltura = 1;
                n3.saldoAltura = 0;
            } else {
                n2.saldoAltura = 0;
                n3.saldoAltura = -1;
            }
        }
    }

    public Empregado buscar(int chave) {
        if (raiz != null)
            return buscar(raiz, chave);
        else
            return null;
    }

    private Empregado buscar(No no, int chave) {
        if (no.conteudo.chave() == chave)
            return no.conteudo;
        else if (no.conteudo.chave() > chave && no.direita != null)
            return buscar(no.direita, chave);
        else if (no.conteudo.chave() < chave && no.direita != null)
            return buscar(no.esquerda, chave);
        else
            return null;
    }

}
