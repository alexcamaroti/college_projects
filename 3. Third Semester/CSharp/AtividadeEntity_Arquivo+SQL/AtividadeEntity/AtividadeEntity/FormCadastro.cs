using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AtividadeEntity.localhost;

namespace AtividadeEntity
{
    public partial class FormCadastro : Form
    {
        public FormCadastro()
        {
            InitializeComponent();
        }

        private void FormCadastro_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            int matricula;

            if (textBox1.Text.Equals("")) {
                MessageBox.Show("O campo nome está em branco", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            else if(textBox2.Text.Equals("")) {
                MessageBox.Show("O campo matrícula está em branco", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            else if (Int32.TryParse(textBox2.Text, out matricula))
            {
                funcionario func = new funcionario();
                Service1 sv = new Service1();
                func.nome = textBox1.Text;
                func.matricula = textBox2.Text;
                sv.inserir(func);
                MessageBox.Show("Cadastrado com Sucesso!", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                
            }
            else {

                MessageBox.Show("A matrícula só pode conter números!", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            
            

            
        }
    }
}
