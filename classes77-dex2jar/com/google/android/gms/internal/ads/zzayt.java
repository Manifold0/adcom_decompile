// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.PublicKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import javax.crypto.KeyAgreement;
import java.security.interfaces.ECPrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.KeyFactory;
import java.security.spec.ECPoint;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECField;
import java.security.GeneralSecurityException;
import java.security.spec.ECFieldFp;
import java.math.BigInteger;
import java.security.spec.EllipticCurve;

public final class zzayt
{
    private static BigInteger zza(final EllipticCurve ellipticCurve) throws GeneralSecurityException {
        final ECField field = ellipticCurve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp)field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }
    
    public static KeyPair zza(final ECParameterSpec ecParameterSpec) throws GeneralSecurityException {
        final KeyPairGenerator keyPairGenerator = zzayy.zzdoe.zzek("EC");
        keyPairGenerator.initialize(ecParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }
    
    public static ECPublicKey zza(final zzayv zzayv, final byte[] array, final byte[] array2) throws GeneralSecurityException {
        final ECParameterSpec zza = zza(zzayv);
        final ECPoint ecPoint = new ECPoint(new BigInteger(1, array), new BigInteger(1, array2));
        zza(ecPoint, zza.getCurve());
        return (ECPublicKey)zzayy.zzdof.zzek("EC").generatePublic(new ECPublicKeySpec(ecPoint, zza));
    }
    
    public static ECParameterSpec zza(final zzayv zzayv) throws NoSuchAlgorithmException {
        switch (zzayu.zzdnn[zzayv.ordinal()]) {
            default: {
                final String value = String.valueOf(zzayv);
                throw new NoSuchAlgorithmException(new StringBuilder(String.valueOf(value).length() + 22).append("curve not implemented:").append(value).toString());
            }
            case 1: {
                return zza("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
            }
            case 2: {
                return zza("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
            }
            case 3: {
                return zza("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
            }
        }
    }
    
    private static ECParameterSpec zza(final String s, final String s2, final String s3, final String s4, final String s5) {
        final BigInteger bigInteger = new BigInteger(s);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger), bigInteger.subtract(new BigInteger("3")), new BigInteger(s3, 16)), new ECPoint(new BigInteger(s4, 16), new BigInteger(s5, 16)), new BigInteger(s2), 1);
    }
    
    static void zza(final ECPoint ecPoint, final EllipticCurve ellipticCurve) throws GeneralSecurityException {
        final BigInteger zza = zza(ellipticCurve);
        final BigInteger affineX = ecPoint.getAffineX();
        final BigInteger affineY = ecPoint.getAffineY();
        if (affineX == null || affineY == null) {
            throw new GeneralSecurityException("point is at infinity");
        }
        if (affineX.signum() == -1 || affineX.compareTo(zza) != -1) {
            throw new GeneralSecurityException("x is out of range");
        }
        if (affineY.signum() == -1 || affineY.compareTo(zza) != -1) {
            throw new GeneralSecurityException("y is out of range");
        }
        if (!affineY.multiply(affineY).mod(zza).equals(affineX.multiply(affineX).add(ellipticCurve.getA()).multiply(affineX).add(ellipticCurve.getB()).mod(zza))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }
    
    public static byte[] zza(final ECPrivateKey ecPrivateKey, final ECPoint ecPoint) throws GeneralSecurityException {
        zza(ecPoint, ecPrivateKey.getParams().getCurve());
        final ECParameterSpec params = ecPrivateKey.getParams();
        params.getCurve();
        final PublicKey generatePublic = KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(ecPoint, params));
        final KeyAgreement keyAgreement = zzayy.zzdod.zzek("ECDH");
        keyAgreement.init(ecPrivateKey);
        keyAgreement.doPhase(generatePublic, true);
        final byte[] generateSecret = keyAgreement.generateSecret();
        final EllipticCurve curve = ecPrivateKey.getParams().getCurve();
        final BigInteger bigInteger = new BigInteger(1, generateSecret);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(zza(curve)) != -1) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        final BigInteger zza = zza(curve);
        final BigInteger mod = bigInteger.multiply(bigInteger).add(curve.getA()).multiply(bigInteger).add(curve.getB()).mod(zza);
        if (zza.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        final BigInteger mod2 = mod.mod(zza);
        final BigInteger bigInteger2 = null;
        BigInteger zero = null;
        Label_0224: {
            if (mod2.equals(BigInteger.ZERO)) {
                zero = BigInteger.ZERO;
            }
            else {
                BigInteger modPow;
                if (zza.testBit(0) && zza.testBit(1)) {
                    modPow = mod2.modPow(zza.add(BigInteger.ONE).shiftRight(2), zza);
                }
                else {
                    modPow = bigInteger2;
                    if (zza.testBit(0)) {
                        modPow = bigInteger2;
                        if (!zza.testBit(1)) {
                            BigInteger one = BigInteger.ONE;
                            final BigInteger shiftRight = zza.subtract(BigInteger.ONE).shiftRight(1);
                            int n = 0;
                            BigInteger mod3 = null;
                            Label_0489: {
                                while (true) {
                                    mod3 = one.multiply(one).subtract(mod2).mod(zza);
                                    zero = one;
                                    if (mod3.equals(BigInteger.ZERO)) {
                                        break Label_0224;
                                    }
                                    final BigInteger modPow2 = mod3.modPow(shiftRight, zza);
                                    if (modPow2.add(BigInteger.ONE).equals(zza)) {
                                        break Label_0489;
                                    }
                                    if (!modPow2.equals(BigInteger.ONE)) {
                                        break;
                                    }
                                    final BigInteger add = one.add(BigInteger.ONE);
                                    final int n2 = ++n;
                                    one = add;
                                    if (n2 != 128) {
                                        continue;
                                    }
                                    n = n2;
                                    one = add;
                                    if (!zza.isProbablePrime(80)) {
                                        throw new InvalidAlgorithmParameterException("p is not prime");
                                    }
                                }
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                            final BigInteger shiftRight2 = zza.add(BigInteger.ONE).shiftRight(1);
                            BigInteger one2 = BigInteger.ONE;
                            int i = shiftRight2.bitLength() - 2;
                            BigInteger bigInteger3 = one;
                            while (i >= 0) {
                                final BigInteger multiply = bigInteger3.multiply(one2);
                                BigInteger mod4 = bigInteger3.multiply(bigInteger3).add(one2.multiply(one2).mod(zza).multiply(mod3)).mod(zza);
                                BigInteger bigInteger4 = multiply.add(multiply).mod(zza);
                                if (shiftRight2.testBit(i)) {
                                    final BigInteger mod5 = mod4.multiply(one).add(bigInteger4.multiply(mod3)).mod(zza);
                                    bigInteger4 = one.multiply(bigInteger4).add(mod4).mod(zza);
                                    mod4 = mod5;
                                }
                                --i;
                                final BigInteger bigInteger5 = bigInteger4;
                                bigInteger3 = mod4;
                                one2 = bigInteger5;
                            }
                            modPow = bigInteger3;
                        }
                    }
                }
                if ((zero = modPow) != null) {
                    zero = modPow;
                    if (modPow.multiply(modPow).mod(zza).compareTo(mod2) != 0) {
                        throw new GeneralSecurityException("Could not find a modular square root");
                    }
                }
            }
        }
        if (!zero.testBit(0)) {
            zza.subtract(zero).mod(zza);
        }
        return generateSecret;
    }
    
    public static int zzb(final EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zza(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }
}
