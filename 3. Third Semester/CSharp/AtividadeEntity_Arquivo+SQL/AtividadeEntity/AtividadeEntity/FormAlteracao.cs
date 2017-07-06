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
    public partial class FormAlteracao : Form
    {
        Form1 auxForm;
        public FormAlteracao(Form1 form)
        {
            auxForm = form;
            InitializeComponent();
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
            else if (textBox2.Text.StartsWith("0"))
            {
                MessageBox.Show("A matricula não pode conter inialmente o número zero", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Warning);

            }else if (Int32.TryParse(textBox2.Text, out matricula))
            {
                funcionario func = new funcionario();
                func.codigo = auxForm.dataGridView1.SelectedRows[0].Index;
                Service1 sv = new Service1();
                func.matricula = textBox2.Text;
                func.nome = textBox1.Text;
                sv.alterar(func);

            }
            else 
            {
                MessageBox.Show("O campo matrícula só pode conter números", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }
    }
}
