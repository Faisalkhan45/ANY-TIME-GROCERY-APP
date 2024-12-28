package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzach  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzach implements zzaev {
    private final zzacg zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzach(zzacg zzacg) {
        zzadl.zzf(zzacg, "input");
        this.zza = zzacg;
        zzacg.zzc = this;
    }

    private final void zzP(Object obj, zzaew zzaew, zzacs zzacs) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzaew.zzh(obj, this, zzacs);
            if (this.zzb != this.zzc) {
                throw zzadn.zzg();
            }
        } finally {
            this.zzc = i;
        }
    }

    private final void zzQ(Object obj, zzaew zzaew, zzacs zzacs) throws IOException {
        int zze = ((zzace) this.zza).zze();
        zzacg zzacg = this.zza;
        if (zzacg.zza < zzacg.zzb) {
            int zzc2 = zzacg.zzc(zze);
            this.zza.zza++;
            zzaew.zzh(obj, this, zzacs);
            this.zza.zzm(0);
            zzacg zzacg2 = this.zza;
            zzacg2.zza--;
            zzacg2.zzn(zzc2);
            return;
        }
        throw new zzadn("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzb() != i) {
            throw zzadn.zzi();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzadn.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzadn.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzadn.zzg();
        }
    }

    public static zzach zzq(zzacg zzacg) {
        zzach zzach = zzacg.zzc;
        return zzach != null ? zzach : new zzach(zzacg);
    }

    public final void zzA(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzadz.zzf(((zzace) this.zza).zzg());
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadz.zzf(((zzace) this.zza).zzg());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzg()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzB(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzacz) {
            zzacz zzacz = (zzacz) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzacz.zze(Float.intBitsToFloat(((zzace) this.zza).zzd()));
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzadn.zza();
            }
            do {
                zzacz.zze(Float.intBitsToFloat(((zzace) this.zza).zzd()));
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzace) this.zza).zzd())));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Float.valueOf(Float.intBitsToFloat(((zzace) this.zza).zzd())));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Deprecated
    public final void zzC(List list, zzaew zzaew, zzacs zzacs) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                Object zze = zzaew.zze();
                zzP(zze, zzaew, zzacs);
                zzaew.zzf(zze);
                list.add(zze);
                zzacg zzacg = this.zza;
                if (!zzacg.zzp() && this.zzd == 0) {
                    zzf = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzadn.zza();
    }

    public final void zzD(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadg.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(((zzace) this.zza).zze());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzE(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadz.zzf(((zzace) this.zza).zzh());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadz.zzf(((zzace) this.zza).zzh());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzh()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzF(List list, zzaew zzaew, zzacs zzacs) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                Object zze = zzaew.zze();
                zzQ(zze, zzaew, zzacs);
                zzaew.zzf(zze);
                list.add(zze);
                zzacg zzacg = this.zza;
                if (!zzacg.zzp() && this.zzd == 0) {
                    zzf = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzadn.zza();
    }

    public final void zzG(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzadg.zzf(((zzace) this.zza).zzd());
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(((zzace) this.zza).zzd());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zzd()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzH(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzadz.zzf(((zzace) this.zza).zzg());
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadz.zzf(((zzace) this.zza).zzg());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzg()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzI(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadg.zzf(zzace.zzs(((zzace) this.zza).zze()));
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(zzace.zzs(((zzace) this.zza).zze()));
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(zzace.zzs(((zzace) this.zza).zze())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(zzace.zzs(((zzace) this.zza).zze())));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzJ(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadz.zzf(zzace.zzt(((zzace) this.zza).zzh()));
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadz.zzf(zzace.zzt(((zzace) this.zza).zzh()));
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(zzace.zzt(((zzace) this.zza).zzh())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(zzace.zzt(((zzace) this.zza).zzh())));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzf;
        int zzf2;
        if ((this.zzb & 7) != 2) {
            throw zzadn.zza();
        } else if ((list instanceof zzads) && !z) {
            zzads zzads = (zzads) list;
            do {
                zzads.zzi(zzp());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        } else {
            do {
                list.add(z ? zzs() : zzr());
                zzacg zzacg2 = this.zza;
                if (!zzacg2.zzp()) {
                    zzf = zzacg2.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
        }
    }

    public final void zzL(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadg.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(((zzace) this.zza).zze());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzM(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadz.zzf(((zzace) this.zza).zzh());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadz.zzf(((zzace) this.zza).zzh());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzh()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzq();
    }

    public final boolean zzO() throws IOException {
        int i;
        zzacg zzacg = this.zza;
        if (zzacg.zzp() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return zzacg.zzr(i);
    }

    public final double zza() throws IOException {
        zzS(1);
        return Double.longBitsToDouble(((zzace) this.zza).zzg());
    }

    public final float zzb() throws IOException {
        zzS(5);
        return Float.intBitsToFloat(((zzace) this.zza).zzd());
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzf();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    public final int zzf() throws IOException {
        zzS(5);
        return ((zzace) this.zza).zzd();
    }

    public final int zzg() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    public final int zzh() throws IOException {
        zzS(5);
        return ((zzace) this.zza).zzd();
    }

    public final int zzi() throws IOException {
        zzS(0);
        return zzace.zzs(((zzace) this.zza).zze());
    }

    public final int zzj() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    public final long zzk() throws IOException {
        zzS(1);
        return ((zzace) this.zza).zzg();
    }

    public final long zzl() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zzh();
    }

    public final long zzm() throws IOException {
        zzS(1);
        return ((zzace) this.zza).zzg();
    }

    public final long zzn() throws IOException {
        zzS(0);
        return zzace.zzt(((zzace) this.zza).zzh());
    }

    public final long zzo() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zzh();
    }

    public final zzacc zzp() throws IOException {
        zzS(2);
        return this.zza.zzj();
    }

    public final String zzr() throws IOException {
        zzS(2);
        return this.zza.zzk();
    }

    public final String zzs() throws IOException {
        zzS(2);
        return this.zza.zzl();
    }

    public final void zzt(Object obj, zzaew zzaew, zzacs zzacs) throws IOException {
        zzS(3);
        zzP(obj, zzaew, zzacs);
    }

    public final void zzu(Object obj, zzaew zzaew, zzacs zzacs) throws IOException {
        zzS(2);
        zzQ(obj, zzaew, zzacs);
    }

    public final void zzv(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzabr) {
            zzabr zzabr = (zzabr) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzabr.zze(this.zza.zzq());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzabr.zze(this.zza.zzq());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Boolean.valueOf(this.zza.zzq()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzq()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzw(List list) throws IOException {
        int zzf;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
            return;
        }
        throw zzadn.zza();
    }

    public final void zzx(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzacp) {
            zzacp zzacp = (zzacp) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzacp.zze(Double.longBitsToDouble(((zzace) this.zza).zzg()));
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzacp.zze(Double.longBitsToDouble(((zzace) this.zza).zzg()));
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzace) this.zza).zzg())));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Double.valueOf(Double.longBitsToDouble(((zzace) this.zza).zzg())));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzy(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadg.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(((zzace) this.zza).zze());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzz(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzace) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzadg.zzf(((zzace) this.zza).zzd());
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzadn.zza();
            }
            do {
                zzadg.zzf(((zzace) this.zza).zzd());
                zzacg zzacg = this.zza;
                if (!zzacg.zzp()) {
                    zzf2 = zzacg.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzace) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zzd()));
            zzacg zzacg2 = this.zza;
            if (!zzacg2.zzp()) {
                zzf = zzacg2.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }
}
