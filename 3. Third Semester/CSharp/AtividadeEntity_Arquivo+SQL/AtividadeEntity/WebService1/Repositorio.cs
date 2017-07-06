using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebService1
{
    public class Repositorio
    {
        private static Repositorio instancia;


        public static Repositorio obterInstancia()
        {
            if (instancia == null)
            {
                instancia = new Repositorio();
            }
            return instancia;
        }

        public void inserir(funcionario func)
        {
            funcionario f = new funcionario();
            empresaEntities bd = new empresaEntities();

            f.nome = func.nome;
            f.matricula = func.matricula;
            bd.AddTofuncionario(f);
            bd.SaveChanges(true);
        }

        public funcionario[] Listar()
        {
            List<funcionario> lista = new List<funcionario>();
            empresaEntities bd = new empresaEntities();

            foreach (var item in bd.funcionario)
            {
                lista.Add(item);
            }
           
            return lista.ToArray(); 
        }

        public void excluir(int index) 
        {
            empresaEntities bd = new empresaEntities();
            funcionario f = bd.funcionario.ToList()[index];
            bd.DeleteObject(f);
            bd.SaveChanges(true);
        
        }

        public void alterar(funcionario func)
        {
            empresaEntities bd = new empresaEntities();
            funcionario f = bd.funcionario.ToList()[func.codigo];
            f.nome = func.nome;
            f.matricula = func.matricula;
            bd.SaveChanges(true);
        
        }
    }
}