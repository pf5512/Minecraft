package net.minecraft.src;

import java.io.File;
import java.io.FilenameFilter;

class AnvilSaveConverterFileFilter implements FilenameFilter
{
	final AnvilSaveConverter parent;
	
	AnvilSaveConverterFileFilter(AnvilSaveConverter par1AnvilSaveConverter)
	{
		parent = par1AnvilSaveConverter;
	}
	
	@Override public boolean accept(File par1File, String par2Str)
	{
		return par2Str.endsWith(".mcr");
	}
}
