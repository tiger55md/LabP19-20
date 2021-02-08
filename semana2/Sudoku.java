import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
	
	/**
	 * 
	 * @param ficheiro que contem o puzzle sudoku
	 * @requires fileIn != null
	 * @return o puzzle se estiver errado, ou mensagem de sucesso se estiver certo
	 * @throws FileNotFoundException
	 */


	public static String verificaQuadrado(String fileIn) throws FileNotFoundException {
		int[][] a = new int[9][9];
		Scanner sc = new Scanner(new File(fileIn));
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		
		if((verificaLinha(a) && verificaColuna(a) && verificaMiniQuadrado(a)) == false) {
			return "O puzzle seguinte está errado:"
					+ arrayToString(a);
		}
		else {
			return "O puzzle respeita as regras do Sudoku";
		}


	}
	
	/**
	 * 
	 * @param matriz dos valores do sudoku
	 * @return se a linha tem valores repetidos
	 */
	private static boolean verificaLinha(int[][] a) {
		for(int linha = 0; linha < a.length; linha++) {
			for(int coluna = 0; coluna < a[linha].length-1; coluna++) {
				for(int coluna1 = coluna +1; coluna1 < 9; coluna1++) {
					if(a[linha][coluna] == a[linha][coluna1]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param matriz dos valores do sudoku
	 * @return se a coluna tem valores repetidos
	 */
	
	private static boolean verificaColuna(int[][] a) {
		for(int linha = 0; linha < a.length; linha++) {
			for(int coluna = 0; coluna < a[linha].length-1; coluna++) {
				for(int coluna1 = coluna +1; coluna1 < 9; coluna1++) {
					if(a[coluna][linha] == a[coluna1][linha]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param matriz dos valores do sudoku
	 * @return se as mini matrizes 3x3 tem valores repetidos
	 */

	private static boolean verificaMiniQuadrado(int[][] a) {
		for(int linha = 0; linha < 9; linha+=3) {
			for(int coluna = 0; coluna < 9; coluna+=3) {
				for(int pos1 = 0; pos1 < 8; pos1++) {
					for(int pos2 = pos1 +1; pos2 < 9; pos2++) {
						if(a[linha+pos1%3][coluna+pos1/3] == a[linha+pos2%3][coluna+pos2/3]) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param matriz dos valores do sudoku
	 * @return string com o puzzle organizado
	 */
	private static String arrayToString(int[][] a) {
		StringBuilder sb = new StringBuilder("\n+-----+-----+-----+ \n");
		for(int i = 0; i < 9; i++) {
			if( i % 3 == 0 && i != 0) {
				sb.append("+-----+-----+-----+ \n");
			}
			for(int j = 0; j < 9; j++) {
				if(j%3 == 0) {
					sb.append("|");
				}
				if(j != 2 && j != 5 && j != 8) {
					sb.append(a[i][j] + " ");
				}
				else {
					sb.append(a[i][j]);
				}
			}
			sb.append("|\n");
		}
		sb.append("+-----+-----+-----+");
		
		return sb.toString();
	}

}
