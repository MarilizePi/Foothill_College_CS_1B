class Communicator extends InternetUser {

	public static final int ERROR_FLAG_NUM = 0;
	public static final long MAX_PQ = (long) Math.sqrt(Long.MAX_VALUE);
	private IntPair publicKey;
	private IntPair privateKey;
	private long firstPrime;
	private long secondPrime;
	private long n, phi, e, d;

	// Constructor with 2 parameters
	public Communicator(long firstPrime, long secondPrime) {
		if (!setPrimes(firstPrime, secondPrime)) {
			setDefault(ERROR_FLAG_NUM);
		}
	}

	// constructor with 2 parameters from IP and name
	public Communicator(String name, String ip) {
		super(name, ip);
		setDefault(ERROR_FLAG_NUM);
	}

	// constructor with 4 parameters name, ip, firstPrime, secondPrime
	public Communicator(String name, String ip, long firstPrime, long secondPrime) {
		super(name, ip);
		if (!setPrimes(firstPrime, secondPrime)) {
			setDefault(ERROR_FLAG_NUM);
		}
	}

	// default constructor
	public Communicator() {
		super();
		setDefault(ERROR_FLAG_NUM);
	}

	// mutator
	public boolean setPrimes(long firstPrime, long secondPrime) {
		if (isValidPrimes(firstPrime, secondPrime)) {
			this.firstPrime = firstPrime;
			this.secondPrime = secondPrime;
			computeBothEncrKeys(firstPrime, secondPrime);
			return true;
		}
		return false;
	}

	private boolean computeBothEncrKeys(long p, long q) {
		if (!isValidPrimes(p, q)) {
			return false;
		}
		n = p * q;
		phi = (p - 1) * (q - 1);
		int attempts = 0;
		while (e > phi || e <= 0 || phi % e == 0 && attempts < 10) {
			e = EncryptionSupport.getSmallRandomPrime();
			attempts++;
		}
		if (e > phi || e <= 0 || phi % e == 0) {
			e = ERROR_FLAG_NUM;
			return false;
		}
		d = EncryptionSupport.inverseModN(e, n);
		publicKey = new IntPair(e, n);
		privateKey = new IntPair(d, n);
		return true;
	}

	// String Representation
	public String toString() {
		String output = "(p, q) n, phi, e, d: (" + firstPrime + ", " + secondPrime + ") " + "  " + n + ", " + phi + ", "
				+ e + ", " + d + "\n";
		output += "public key: " + publicKey.toString() + "\n" + "private key: " + privateKey.toString() + "\n";
		return output;
	}

	// default value all are zero
	private void setDefault(int value) {
		publicKey = new IntPair();
		privateKey = new IntPair();
		phi = e = n = d = value;
		firstPrime = secondPrime = value;
	}

	IntPair getPublicKey() {
		return publicKey;
	}

	IntPair getPrivateKey() {
		return privateKey;
	}

	private static boolean isValidPrimes(long p, long q) {
		if (EncryptionSupport.isPrime(p) && EncryptionSupport.isPrime(q) && p <= MAX_PQ && q <= MAX_PQ && p != q) {
			return true;
		}
		return false;
	}
}