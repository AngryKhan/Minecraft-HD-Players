package net.minecraft.src;

import java.awt.Graphics;
import java.awt.image.*;

public class ImageBufferDownload implements IImageBuffer
{
    private int[] imageData;
    private int imageWidth;
    private int imageHeight;

    public BufferedImage parseUserSkin(BufferedImage par1BufferedImage)
    {
        if (par1BufferedImage == null)
        {
            return null;
        }
        else
        {
            this.imageWidth = par1BufferedImage.getWidth(null);
			this.imageHeight = this.imageWidth / 2;
            BufferedImage var2 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
            Graphics var3 = var2.getGraphics();
            var3.drawImage(par1BufferedImage, 0, 0, (ImageObserver)null);
            var3.dispose();
            this.imageData = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();

            boolean var4 = false;
            int var5;
            int var6;
            int var7;

            for (var5 = 32; var5 < 64; ++var5)
            {
                for (var6 = 0; var6 < 16; ++var6)
                {
                    var7 = this.imageData[var5 + var6 * 64];

                    if ((var7 >> 24 & 255) < 128)
                    {
                        var4 = true;
                    }
                }
            }

            if (!var4)
            {
                for (var5 = 32; var5 < 64; ++var5)
                {
                    for (var6 = 0; var6 < 16; ++var6)
                    {
                        var7 = this.imageData[var5 + var6 * 64];

                        if ((var7 >> 24 & 255) < 128)
                        {
                            var4 = true;
                        }
                    }
                }
            }

            return var2;
        }
    }

    private void func_78434_a(int par1, int par2, int par3, int par4)
    {
        if (!this.func_78435_c(par1, par2, par3, par4))
        {
            for (int var5 = par1; var5 < par3; ++var5)
            {
                for (int var6 = par2; var6 < par4; ++var6)
                {
                    this.imageData[var5 + var6 * this.imageWidth] &= 16777215;
                }
            }
        }
    }

    private void func_78433_b(int par1, int par2, int par3, int par4)
    {
        for (int var5 = par1; var5 < par3; ++var5)
        {
            for (int var6 = par2; var6 < par4; ++var6)
            {
                this.imageData[var5 + var6 * this.imageWidth] |= -16777216;
            }
        }
    }

    private boolean func_78435_c(int par1, int par2, int par3, int par4)
    {
        for (int var5 = par1; var5 < par3; ++var5)
        {
            for (int var6 = par2; var6 < par4; ++var6)
            {
                int var7 = this.imageData[var5 + var6 * this.imageWidth];

                if ((var7 >> 24 & 255) < 128)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
