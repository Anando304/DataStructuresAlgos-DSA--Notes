/****************************************************************************
* Compilation: javac PotentialGene.java
* Execution: java PotentialGene < input.txt
*
* Determines whether a a DNA string corresponds to a potential gene
* - length is a multiple of 3
* - starts with the start codon (ATG)
* - ends with a stop codon (TAA or TAG or TGA)
* - has no intervening stop codons (i.e. a stop codon cannot be in the
** middle of the string.
*
* % java PotentialGene ATGCGCCTGCGTCTGTACTAG
* true
*
* % java PotentialGene ATGCGCTGCGTCTGTACTAG
* false
*
****************************************************************************/
public class PotentialGene {

	
	public static boolean isPotentialGene(String dna) {
		/*Variables used to keep track of each condition tested*/
		boolean length_condition = false;
		boolean start = false;
		boolean end = false;
		boolean middle_stop_codons = true; //Initially set to true but becomes false if intervening stop codons occur.
		
		//Verify length
		if(dna.length()%3==0) {
			length_condition = true;
		}
		
		//Verify starting
		if(dna.startsWith("ATG")) {
			start = true;
		}
		
		//Verify ending
		if(dna.endsWith("TAA") || dna.endsWith("TAG") || dna.endsWith("TGA")) {
			end = true;
		}
		
		//Verify for intervening stop codons
		for(int i = 3; i<=dna.length()-4; i+=3) { //Start at index 2 and end at index length-4 so that startsWith and endsWith not included
			if((dna.substring(i, i+3).equals("TAA")) || (dna.substring(i, i+3).equals("TAG")) || (dna.substring(i, i+3).equals("TGA"))) {
				middle_stop_codons = false;
			}
		}
		
		return length_condition && start && end && middle_stop_codons;
		}
	
	public static void main(String[] args) {
		String dna = args[0];
		System.out.println(isPotentialGene(dna));
		}

}
